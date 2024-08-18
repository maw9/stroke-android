package com.stroke.stroke_android.feature.home.data.datasource

import com.stroke.stroke_android.feature.home.ui.model.Post
import com.stroke.stroke_android.feature.home.ui.model.User

class MockHomePostsDataSourceImpl : HomePostsDataSource {

    val user1 =
        User(id = "user1", name = "Alice", profileImageUrl = "https://static.vecteezy.com/system/resources/previews/004/819/327/original/male-avatar-profile-icon-of-smiling-caucasian-man-vector.jpg")
    val user2 = User(id = "user2", name = "Bob", profileImageUrl = "https://static.vecteezy.com/system/resources/previews/004/819/327/original/male-avatar-profile-icon-of-smiling-caucasian-man-vector.jpg")
    val user3 =
        User(id = "user3", name = "Charlie", profileImageUrl = "https://static.vecteezy.com/system/resources/previews/004/819/327/original/male-avatar-profile-icon-of-smiling-caucasian-man-vector.jpg")
    val user4 =
        User(id = "user4", name = "David", profileImageUrl = "https://static.vecteezy.com/system/resources/previews/004/819/327/original/male-avatar-profile-icon-of-smiling-caucasian-man-vector.jpg")
    val user5 = User(id = "user5", name = "Eve", profileImageUrl = "https://static.vecteezy.com/system/resources/previews/004/819/327/original/male-avatar-profile-icon-of-smiling-caucasian-man-vector.jpg")
    val user6 =
        User(id = "user6", name = "Frank", profileImageUrl = "https://static.vecteezy.com/system/resources/previews/004/819/327/original/male-avatar-profile-icon-of-smiling-caucasian-man-vector.jpg")
    val user7 =
        User(id = "user7", name = "Grace", profileImageUrl = "https://static.vecteezy.com/system/resources/previews/004/819/327/original/male-avatar-profile-icon-of-smiling-caucasian-man-vector.jpg")

    val post1 = Post(
        id = "post1",
        description = "This is a sample post.",
        createdAt = "2023-12-31T23:59:59Z",
        imageUrl = "https://samuelearp.com/wp-content/uploads/2023/05/NorahHeadAustralia-Seascape-SamuelEarp-Oilpainting.jpg",
        owner = user1,
        isFavorite = false
    )
    val post2 = Post(
        id = "post2",
        description = "Another post!",
        createdAt = "2024-01-01T00:00:00Z",
        imageUrl = "https://samuelearp.com/wp-content/uploads/2023/05/NorahHeadAustralia-Seascape-SamuelEarp-Oilpainting.jpg",
        owner = user2,
        isFavorite = true
    )
    val post3 = Post(
        id = "post3",
        description = "Interesting post.",
        createdAt = "2024-01-02T01:01:01Z",
        imageUrl = "https://samuelearp.com/wp-content/uploads/2023/05/NorahHeadAustralia-Seascape-SamuelEarp-Oilpainting.jpg",
        owner = user3,
        isFavorite = false
    )
    val post4 = Post(
        id = "post4",
        description = "Great post!",
        createdAt = "2024-01-03T02:02:02Z",
        imageUrl = "https://samuelearp.com/wp-content/uploads/2023/05/NorahHeadAustralia-Seascape-SamuelEarp-Oilpainting.jpg",
        owner = user4,
        isFavorite = true
    )
    val post5 = Post(
        id = "post5",
        description = "Amazing post.",
        createdAt = "2024-01-04T03:03:03Z",
        imageUrl = "https://samuelearp.com/wp-content/uploads/2023/05/NorahHeadAustralia-Seascape-SamuelEarp-Oilpainting.jpg",
        owner = user5,
        isFavorite = false
    )
    val post6 = Post(
        id = "post6",
        description = "Wonderful post.",
        createdAt = "2024-01-05T04:04:04Z",
        imageUrl = "https://samuelearp.com/wp-content/uploads/2023/05/NorahHeadAustralia-Seascape-SamuelEarp-Oilpainting.jpg",
        owner = user6,
        isFavorite = true
    )
    val post7 = Post(
        id = "post7",
        description = "Fantastic post.",
        createdAt = "2024-01-06T05:05:05Z",
        imageUrl = "https://samuelearp.com/wp-content/uploads/2023/05/NorahHeadAustralia-Seascape-SamuelEarp-Oilpainting.jpg",
        owner = user7,
        isFavorite = false
    )

    override suspend fun getPosts(): Result<List<Post>> {
        return Result.success(
            listOf(
                post1,
                post2,
                post3,
                post4,
                post5,
                post6,
                post7
            )
        )
    }

}