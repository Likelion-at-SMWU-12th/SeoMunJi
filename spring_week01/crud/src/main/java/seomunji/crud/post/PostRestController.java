package seomunji.crud.post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("post")
public class PostRestController{
    private static final Logger logger= LoggerFactory.getLogger(PostRestController.class);
    private final List<PostDto> postList;
    public PostRestController() {
        this.postList = new ArrayList<>();
    }

    // 1 createPost
    // http://localhost:8080/post
    // POST /post

    @PostMapping()
    public void createPost(@RequestBody PostDto postDto){
        logger.info(postDto.toString());
        this.postList.add(postDto);
    }

    // 2 get
    // http://localhost:8080/post
    // GET /post
    @GetMapping()
    public List<PostDto> readPostAll(){
        logger.info("iin read post all");
        return this.postList;
    }

    // GET /post/0/
    // GET /post?id=0/
    @GetMapping("{id}")
    public PostDto readPost(@PathVariable("id") int id){
        logger.info("in read post");
        return this.postList.get(id);
    }

    @PutMapping("/{id}")
    public void updatePost(@PathVariable("id") int id, @RequestBody PostDto postDto) {
        if (id >= 0 && id < postList.size()) {
            PostDto targetPost = this.postList.get(id);
            if (postDto.getTitle() != null) {
                targetPost.setTitle(postDto.getTitle());
            }
            if (postDto.getContent() != null) {
                targetPost.setContent(postDto.getContent());
            }
            if (postDto.getWriter() != null) {
                targetPost.setWriter(postDto.getWriter());
            }
            this.postList.set(id, targetPost);
            logger.info("Updated Post: " + targetPost.toString());
        } else {
            logger.warn("Post with ID " + id + " not found");
        }
    }

    // Delete Post
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable("id") int id) {
        if (id >= 0 && id < postList.size()) {
            this.postList.remove(id);
            logger.info("Deleted Post with ID: " + id);
        } else {
            logger.warn("Post with ID " + id + " not found");
        }
    }
}
