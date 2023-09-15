package com.tunahankaryagdi.b_log.domain.model

import com.tunahankaryagdi.b_log.data.model.AuthorDto
import com.tunahankaryagdi.b_log.data.model.BlogDto
import com.tunahankaryagdi.b_log.data.model.LikeDto
import com.tunahankaryagdi.b_log.data.model.SectionDto


data class Blog(
    val id: String,
    val author: Author,
    val image: String,
    val title: String,
    val tags: List<String>,
    val updatedAt : String,
    val likes : List<Like>
)
fun BlogDto.toBlog() : Blog{
    return Blog(
        id = this.id,
        author = this.author.toAuthor(),
        image = this.image,
        title = this.title,
        tags = this.tags,
        updatedAt = this.updatedAt,
        likes = this.likes.map { it.toLike() }
    )
}
data class Section(
    val content: String,
    val id: String,
    val image: String,
    val title: String,
    val type :String
)

fun SectionDto.toSection() :Section{
    return Section(
        content = this.content,
        id = this.id,
        title = this.title,
        image = this.image,
        type = this.type
    )
}
data class Author(
    val id : String,
    val firstName: String,
    val lastName: String,
    val email: String
)

fun AuthorDto.toAuthor():Author{
    return  Author(
        id = this.id,
        firstName =this.firstName,
        lastName = this.lastName,
        email = this.email
    )
}

data class Like(
    val id: String,
    val blogId: String,
    val userId: String
)


fun LikeDto.toLike() : Like{
    return Like(
        id = this.id,
        blogId = this.blogId,
        userId = this.userId
    )
}