package kr.inhatc.spring.community.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import kr.inhatc.spring.myPage.entity.UserVideo;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QVideo_Board is a Querydsl query type for Video_Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserVideo extends EntityPathBase<UserVideo> {

    private static final long serialVersionUVID = -245474782L;

    public static final QUserVideo userVideo = new QUserVideo("UserVideo");

    public final StringPath UVContents = createString("UVContents");


    public final NumberPath<Integer> UVHitCnt = createNumber("UVHitCnt", Integer.class);
    public final NumberPath<Integer> UVId = createNumber("UVId", Integer.class);

    public final NumberPath<Integer> UId = createNumber("UId", Integer.class);

    public final StringPath UVTitle = createString("UVTitle");

    public final StringPath UVTime = createString("UVTime");
    public final StringPath UVUpload = createString("UVUpload");

    public QUserVideo(String variable) {
        super(UserVideo.class, forVariable(variable));
    }

    public QUserVideo(Path<? extends UserVideo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserVideo(PathMetadata metadata) {
        super(UserVideo.class, metadata);
    }

}

