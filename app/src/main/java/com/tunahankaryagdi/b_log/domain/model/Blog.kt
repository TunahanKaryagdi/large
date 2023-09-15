package com.tunahankaryagdi.b_log.domain.model

import com.tunahankaryagdi.b_log.data.model.AuthorDto
import com.tunahankaryagdi.b_log.data.model.BlogDto
import com.tunahankaryagdi.b_log.data.model.CommentDto
import com.tunahankaryagdi.b_log.data.model.LikeDto
import com.tunahankaryagdi.b_log.data.model.SectionDto


data class Blog(
    val id: String,
    val author: Author,
    val image: String,
    val title: String,
    val tags: List<String>,
    val updatedAt : String
)

data class Section(
    val content: String,
    val id: String,
    val image: String,
    val title: String,
    val type :String
)

data class Author(
    val id : String,
    val firstName: String,
    val lastName: String,
    val email: String
)

data class Comment(
    val blogId: String,
    val content: String,
    val createdAt: String,
    val id: String,
    val updatedAt: String,
    val userId: String
)


data class Like(
    val id: String,
    val blogId: String,
    val userId: String
)




fun BlogDto.toBlog() : Blog{
    return Blog(
        id = this.id,
        author = this.author.toAuthor(),
        image = this.image,
        title = this.title,
        tags = this.tags,
        updatedAt = this.updatedAt
    )
}


fun SectionDto.toSection() :Section{
    return Section(
        content = this.content,
        id = this.id,
        title = this.title,
        image = this.image,
        type = this.type
    )
}


fun AuthorDto.toAuthor():Author{
    return  Author(
        id = this.id,
        firstName =this.firstName,
        lastName = this.lastName,
        email = this.email
    )
}

fun CommentDto.toComment() : Comment {
    return Comment(
        id = this.id,
        blogId = this.blogId,
        userId = this.userId,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        content = this.content
    )
}

fun LikeDto.toLike() : Like{
    return Like(
        id = this.id,
        blogId = this.blogId,
        userId = this.userId
    )
}