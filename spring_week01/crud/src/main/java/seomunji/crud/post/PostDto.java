package seomunji.crud.post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private int id;
    private String title;
    private String content;
    private String writer;
    private int boardId;

    public PostDto(int id, String title, String content, String writer, int boardId){
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.boardId=boardId;
    }
}
/*

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
    @Override
    public String toString(){
        return "PostDto{"+
                "title="+title+'\''+
                ",content="+content+'\''+
                "}";
    }
}
*/
