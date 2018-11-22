package cn.zznlin.simple.common.utils;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.io.UnsupportedEncodingException;

/**
 * @Author zhennan
 * @Date 2018/11/22 0:01
 * @Description
 */
public class HtmlUtils {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String content = "<p><img alt=\"\" class=\"image_right\" height=\"150\" src=\"http://localhost:18800/upload/181115/0583ee70-a8c8-46ac-aa82-d5c67e54933a.jpg\" width=\"150\" />Fringilla nisl. Donec accumsan interdum nisi, quis tincidunt felis sagittis eget. tempus euismod. Vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent tincidunt felis sagittis eget. tempus euismod. Vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent. Donec accumsan interdum nisi, quis tincidunt felis sagittis eget. tempus euismod. Vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent tincidunt felis sagittis eget. tempus euismod. Vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent tincidunt felis sagittis eget. tempus euismod. Vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent.</p>";

        String s = extractContent(content,200);
        System.out.println(s);
    }

    /**
     * v本方法用于提取某个html文档中P标签中的内容
     */
    public static String extractContent(String content,Integer length) throws UnsupportedEncodingException {
        if(length == null || length<=0){
            return "";
        }
        StringBuffer sb = new StringBuffer();
        try {
             // 1、构造一个Parser，并设置相关的属性
            Parser parser = Parser.createParser(content,"UTF-8");

            // 2.1、自定义一个Filter，用于过滤<Frame >标签，然后取得标签中的src属性值
            NodeFilter pNodeFilter = new NodeFilter() {
                @Override
                public boolean accept(Node node) {
                    if (node.getText().startsWith("p")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            };

            //2.2、创建第二个Filter，过滤<a>标签
//            NodeFilter aNodeFilter = new NodeClassFilter(LinkTag.class);
//
//            //2.3、净土上述2个Filter形成一个组合逻辑Filter。
//            OrFilter linkFilter = new OrFilter(frameNodeFilter);

            //3、使用parser根据filter来取得所有符合条件的节点
            NodeList nodeList = parser.extractAllNodesThatMatch(pNodeFilter);

            //4、对取得的Node进行处理
            for(int i = 0; i<nodeList.size();i++){
                Node node = nodeList.elementAt(i);
                String linkURL = "";
                //如果链接类型为<a />
                if(node instanceof LinkTag){
                    LinkTag link = (LinkTag)node;
                    linkURL= link.toPlainTextString();
                }else{
                    //如果类型为P
                    String nodeText = node.toPlainTextString();
                    linkURL = nodeText;
                }
                //判断是否属于本次搜索范围的url
                sb.append(linkURL);
                if(sb.length() >= length){
                    break;
                }
            }

        } catch (ParserException e) {
            e.printStackTrace();
        }
        if(sb.length() < length){
            return sb.toString();
        }else{
            return sb.toString().substring(0, length);
        }
    }

}


