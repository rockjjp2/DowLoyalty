package com.dowloyalty.pojo.wechat;
/**
 * @Description: 文本消息
 * @author zhoum
 *
 */
public class TextMessage extends BaseMessage{  
    // 消息内容   
    private String Content;  

    public String getContent() {  
        return Content;  
    }  

    public void setContent(String content) {  
        Content = content;  
    }

	@Override
	public String toString() {
		return "TextMessage [Content=" + Content + "]";
	}  
    
}
