package com.hodolog.service;

import com.hodolog.domain.Post;
import com.hodolog.domain.PostEditor;
import com.hodolog.repository.PostRepository;
import com.hodolog.request.PostCreate;
import com.hodolog.request.PostEdit;
import com.hodolog.request.PostSearch;
import com.hodolog.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void write(PostCreate postCreate) {
        Post post = Post.builder()
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .build();

        postRepository.save(post);
    }

    public PostResponse get(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글 입니다."));

        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

    public List<PostResponse> getList(PostSearch postSearch) {
        return postRepository.getList(postSearch).stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void edit(Long id, PostEdit postEdit) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));

        PostEditor.PostEditorBuilder editorBuilder = post.toEditor();

        PostEditor postEditor = editorBuilder.title(postEdit.getTitle())
                .content(postEdit.getContent())
                .build();

        post.edit(postEditor);

        // PostEditor 로 작성한 이유는 edit 필드가 많아지면 아래처럼 처리하기 힘듭니다.
        // Post 가 스스로 수정할 수 있는 포인트는 타이틀과 제목 같은 제한 포인트를 두기 위해 PostEditor 를 사용합니다.
        // 프론트 개발자 입장에서 생각해보면, 제목: 호돌맨 내용: 반포자이-> 초가집 으로 수정한다고 가정합니다.
        // 타이틀은 수정안하니까 null 로 보내고, 컨텐트는 초가집으로 보내게 되는 경우가 있습니다.
        // 아래처럼하면 검증을 안하고 그냥 postEdit.getTitle() 을 때려 넣은 것이기 때문에 문제가 됩니다.
        // postEdit.getTitle() != null ? postEdit.getTitle() : post.getTitle(); 으로 처리해야 합니다.
        // 기존의 저장된 제목을 유지하거나 수정된 제목을 넣어야 한다는 것입니다.
        // 이러한 문제점은 코드 가독성이나 유지 보수가 어렵기 때문에 PostEditor 을 사용하는 것입니다.
//        post.edit(postEdit.getTitle(), postEdit.getContent());

    }

//    public void delete(Long id {
//        Post post = postRepository.findById(id)
//                .orElseThrow(PostNotFound::new);
//
//        postRepository.delete(post);
//    })
}
