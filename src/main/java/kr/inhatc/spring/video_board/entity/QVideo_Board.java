package kr.inhatc.spring.video_board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QVideo_Board is a Querydsl query type for Video_Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVideo_Board extends EntityPathBase<Video_Board> {

    private static final long serialVersionUID = -24547478L;

    public static final QVideo_Board video_Board = new QVideo_Board("video_Board");

    public final StringPath contents = createString("contents");

    public final StringPath creator = createString("creator");

    public final NumberPath<Integer> hitCnt = createNumber("hitCnt", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath title = createString("title");

    public final StringPath uploadDate = createString("uploadDate");

    public QVideo_Board(String variable) {
        super(Video_Board.class, forVariable(variable));
    }

    public QVideo_Board(Path<? extends Video_Board> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVideo_Board(PathMetadata metadata) {
        super(Video_Board.class, metadata);
    }

}

