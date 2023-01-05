<template>
  <div class="create-button">
    <el-button
      class="detail-button"
      color="#cf4420"
      type="primary"
      :icon="Plus"
      @click="$router.push('/post/edit')"
      >post</el-button
    >
  </div>

  <div class="post-contents">
    <ul>
      <template v-for="post in postList" :key="post.id">
        <post-card v-bind="post"></post-card>
      </template>
    </ul>
  </div>

  <div class="page-style">
    <el-pagination
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-size="6"
      :total="totalCount"
      layout="prev, pager, next"
    />
  </div>
</template>

<script>
import PostCard from "@/components/PostCard.vue";
import axios from "axios";
import { CirclePlus } from "@element-plus/icons-vue";

export default {
  components: {
    PostCard,
  },
  data() {
    return {
      user: {
        email: "",
        uid: null,
      },
      postList: [],
      Plus: CirclePlus,
      currentPage: 1,
      totalCount: 0,
    };
  },
  mounted() {
    if (sessionStorage.getItem("userInfo")) {
      // store user info into sessionStorage
      let temp = JSON.parse(sessionStorage.getItem("userInfo"));
      this.user.email = temp["email"];
      this.user.uid = temp["id"];
    }

    this.getPostList(this.currentPage);
  },
  watch: {
    $route(to, from) {
      this.getPostList();
    },
  },
  methods: {
    handleCurrentChange: function (val) {
      this.currentPage = val;
      this.getPostList(this.currentPage);
    },
    getPostList(pageNum) {
      let url = "post/me/" + this.user.uid + "?pageNum=" + pageNum;
      axios.get(url).then((res) => {
        this.postList = res.data.content;
        this.totalCount = res.data.totalElements;
      });
    },
  },
};
</script>

<style scoped>
.create-button {
  margin-top: 20px;
  margin-left: 20px;
}

.detail-button {
  height: 50px;
  font-size: 18px;
  text-align: center;
  border: none;
  background: #cf4420;
  color: white;
}

.detail-button:hover {
  background: #630031;
  border: none;
}

.post-contents {
  position: relative;
  top: 20px;
}

.post-contents ul {
  display: flex;
  flex-wrap: wrap;
  padding: 1em;
  gap: 1em;
}
</style>
