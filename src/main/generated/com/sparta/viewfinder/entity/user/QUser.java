package com.sparta.viewfinder.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;
import com.sparta.viewfinder.constant.UserRoleEnum;
import com.sparta.viewfinder.constant.UserStatusEnum;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1406990656L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final com.sparta.viewfinder.entity.constant.QTimestamped _super = new com.sparta.viewfinder.entity.constant.QTimestamped(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final com.sparta.viewfinder.entity.profile.QProfile profile;

    public final StringPath refreshToken = createString("refreshToken");

    public final EnumPath<UserStatusEnum> status = createEnum("status", UserStatusEnum.class);

    public final DateTimePath<java.time.LocalDateTime> statusUpdate = createDateTime("statusUpdate", java.time.LocalDateTime.class);

    public final StringPath username = createString("username");

    public final EnumPath<UserRoleEnum> userRole = createEnum("userRole", UserRoleEnum.class);

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.profile = inits.isInitialized("profile") ? new com.sparta.viewfinder.entity.profile.QProfile(forProperty("profile"), inits.get("profile")) : null;
    }

}

