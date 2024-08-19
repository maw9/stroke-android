package com.stroke.stroke_android.feature.postdetails.data.datasource

import com.stroke.stroke_android.feature.home.ui.model.User
import com.stroke.stroke_android.feature.postdetails.ui.model.PostDetail

class MockPostDetailsDataSourceImpl : PostDetailsDataSource {

    override suspend fun getPostDetails(id: String): Result<PostDetail?> {
        return Result.success(
            PostDetail(
                "0",
                "A person working on a laptop with a pen",
                "2024-07-18T19:49:40Z",
                blurredBitmap = null,
                "https://images.unsplash.com/photo-1721332150382-d4114ee27eff?ixid=M3w2MTQ5NTV8MHwxfGFsbHx8fHx8fHx8fDE3MjM5ODkyNzl8&ixlib=rb-4.0.3",
                User(
                    id = "user7",
                    name = "Grace",
                    profileImageUrl = "https://static.vecteezy.com/system/resources/previews/004/819/327/original/male-avatar-profile-icon-of-smiling-caucasian-man-vector.jpg"
                ),
                true,
                listOf("tag1", "tag2", "tag3", "tag4", "tag5")
            )
        )
    }

}