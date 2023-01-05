<template>
  <div class="explore-view">
    <div class="top-container">
      <div class="search-box">
        <el-form ref="postForm" :model="postForm" label-width="10px">
          <el-row>
            <el-col :span="200"
              ><el-form-item>
                <el-select
                  v-model="postForm.type"
                  placeholder="Want a ..."
                  :disabled="postType !== ''"
                >
                  <template #prefix>
                    <span style="padding-left: 5px">
                      <el-icon><User /></el-icon>
                    </span>
                  </template>
                  <el-option label="Driver" value="0">Driver</el-option>
                  <el-option label="Passenger" value="1">Passenger</el-option>
                </el-select>
              </el-form-item>
              <el-form-item></el-form-item
            ></el-col>
            <el-col :span="200"
              ><el-row>
                <el-form-item>
                  <el-select
                    clearable
                    v-model="postForm.startArea"
                    placeholder="Pick-up area"
                    @visible-change="handleSelectVisibleChange"
                  >
                    <template #prefix>
                      <span style="padding-left: 5px">
                        <el-icon><Location /></el-icon>
                      </span>
                    </template>
                    <el-option
                      v-for="item in areaList"
                      :key="item.id"
                      :label="item.name"
                      :value="item.id"
                    >
                    </el-option>
                  </el-select>
                </el-form-item>
                <el-form-item>
                  <el-select
                    v-model="postForm.spotIdStart"
                    placeholder="Pick-up spot"
                    @visible-change="handleSelectVisibleChange"
                  >
                    <template #prefix>
                      <span style="padding-left: 5px">
                        <el-icon><Location /></el-icon>
                      </span>
                    </template>
                    <el-option
                      v-for="item in startSpotList"
                      :key="item.id"
                      :label="item.location"
                      :value="item.id"
                    >
                    </el-option>
                  </el-select>
                </el-form-item>
                <el-form-item>
                  <el-date-picker
                    v-model="postForm.startTime"
                    type="datetime"
                    placeholder="Pick-up date"
                  />
                </el-form-item>
                <el-form-item>
                  <el-button
                    class="search-button"
                    color="#cf4420"
                    :icon="Search"
                    @click="searchPost(this.currentPage)"
                    >Search</el-button
                  >
                </el-form-item>
              </el-row>
              <el-row>
                <el-form-item>
                  <el-select
                    v-model="postForm.endArea"
                    placeholder="Drop-off area"
                    @visible-change="handleSelectVisibleChange"
                  >
                    <template #prefix>
                      <span style="padding-left: 5px">
                        <el-icon><Location /></el-icon>
                      </span>
                    </template>
                    <el-option
                      v-for="item in areaList"
                      :key="item.id"
                      :label="item.name"
                      :value="item.id"
                    >
                    </el-option>
                  </el-select>
                </el-form-item>
                <el-form-item>
                  <el-select
                    v-model="postForm.spotIdEnd"
                    placeholder="Drop-off spot"
                    @visible-change="handleSelectVisibleChange"
                  >
                    <template #prefix>
                      <span style="padding-left: 5px">
                        <el-icon><Location /></el-icon>
                      </span>
                    </template>
                    <el-option
                      v-for="item in endSpotList"
                      :key="item.id"
                      :label="item.location"
                      :value="item.id"
                    >
                    </el-option>
                  </el-select>
                </el-form-item>
                <el-form-item>
                  <el-date-picker
                    v-model="postForm.endTime"
                    type="datetime"
                    placeholder="Drop-off date"
                /></el-form-item>
                <el-form-item>
                  <el-button
                    class="reset-button"
                    color="#cf4420"
                    type="primary"
                    @click="resetForm"
                    :icon="Refresh"
                    plain
                    >Reset</el-button
                  >
                </el-form-item>
              </el-row></el-col
            >
          </el-row>
        </el-form>
      </div>
      <el-button
        class="search-button create"
        color="#cf4420"
        type="primary"
        @click="$router.push('/post/edit')"
        >POST HERE
      </el-button>
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
        :page-size="3"
        :total="totalCount"
        layout="prev, pager, next"
      />
    </div>
  </div>
</template>

<script>
import PostCard from "@/components/PostCard.vue";
import axios from "axios";
import { Search, Location, User, Refresh } from "@element-plus/icons-vue";

function isEmpty(v) {
  if (v === "" || v === undefined || v === null) {
    return true;
  }

  if (typeof v === "object") {
    if (Array.isArray(v)) {
      return v.length === 0;
    } else {
      return Object.keys(v).length === 0;
    }
  }
  return false;
}

let postForm = {
  startArea: "",
  endArea: "",
  startTime: "",
  endTime: "",
  spotIdStart: "",
  spotIdEnd: "",
  type: "",
  page: "",
  size: "",
};

export default {
  components: {
    PostCard,
    Location,
    User,
  },
  props: {},
  data() {
    return {
      user: {
        email: "",
        uid: null,
      },
      postForm: Object.assign({}, postForm),
      postList: [],
      areaList: [],
      startSpotList: [],
      endSpotList: [],
      isSelectVisible: false,
      Search: Search,
      Refresh: Refresh,
      postType: "",
      currentPage: 1,
      totalCount: 0,
      searching: false,
    };
  },
  watch: {
    "postForm.startArea"(newVal, oldVal) {
      if (this.isSelectVisible) {
        this.postForm.spotIdStart = "";
      }
      if (!isEmpty(newVal)) {
        this.getStartSpotList(newVal);
      }
    },
    "postForm.endArea"(newVal, oldVal) {
      if (this.isSelectVisible) {
        this.postForm.spotIdEnd = "";
      }
      if (!isEmpty(newVal)) {
        this.getEndSpotList(newVal);
      }
    },
  },
  mounted() {
    if (sessionStorage.getItem("userInfo")) {
      // store user info into sessionStorage
      let temp = JSON.parse(sessionStorage.getItem("userInfo"));
      this.user.email = temp["email"];
      this.user.uid = temp["id"];
    }

    this.getPostList(this.currentPage);
    this.getAreaList();
  },
  methods: {
    handleCurrentChange: function (val) {
      this.currentPage = val;
      if (this.searching) {
        this.searchPost(this.currentPage);
      } else {
        this.getPostList(this.currentPage);
      }
    },
    getPostList(pageNum) {
      let url = "post?pageNum=" + pageNum + "&pageSize=3";
      axios.get(url).then((res) => {
        this.postList = res.data.content;
        this.totalCount = res.data.totalElements;
      });
    },
    getAreaList() {
      axios.get("area").then((res) => {
        this.areaList = res.data;
      });
    },
    getStartSpotList(areaId) {
      axios.get("spot/" + areaId).then((res) => {
        this.startSpotList = res.data;
      });
    },
    getEndSpotList(areaId) {
      axios.get("spot/" + areaId).then((res) => {
        this.endSpotList = res.data;
      });
    },
    handleSelectVisibleChange(v) {
      this.isSelectVisible = v;
    },
    searchPost(pageNum) {
      const url = "post";
      const data = {
        startTime:
          this.postForm.startTime === ""
            ? ""
            : this.postForm.startTime
                .toISOString()
                .replace(/T/g, " ")
                .replace(/\.[\d]{3}Z/, ""),
        endTime:
          this.postForm.endTime === ""
            ? ""
            : this.postForm.endTime
                .toISOString()
                .replace(/T/g, " ")
                .replace(/\.[\d]{3}Z/, ""),
        spotIdStart: this.postForm.spotIdStart,
        spotIdEnd: this.postForm.spotIdEnd,
        type: this.postForm.type,
        page: pageNum,
        size: 3,
      };
      axios.post(url, data).then((res) => {
        this.postList = res.data.content;
        this.totalCount = res.data.totalElements;
        this.searching = true;
      });
    },
    resetForm() {
      location.reload();
    },
  },
};
</script>

<style scoped>
.explore-view {
  width: 100%;
  height: 100vh;
  padding: 30px 30px;
  align-items: center;
  justify-content: center;
  background: #eaeaea;
}

.top-container {
  display: flex;
  flex-direction: row;
}

.create {
  width: 200px;
  height: 100px;
  margin: auto;
}

.search-box {
  margin: auto;
  width: 80%;
  height: fit-content;
  border: 1px solid #57575c;
  border-radius: 10px;
  padding: 10px 10px 0 10px;
}

:deep(.el-input__wrapper) {
  border: 1px solid #727483;
  box-shadow: none;
}

:deep(.el-input__inner::placeholder) {
  color: #343b53;
}

.post-contents {
  position: relative;
  top: 20px;
  margin-bottom: 40px;
}

.post-contents ul {
  display: flex;
  flex-wrap: wrap;
  padding: 1em;
  gap: 1em;
}

.search-button {
  background: #cf4420;
  color: white;
  border: none;
}

.search-button:hover,
.reset-button:hover {
  color: white;
  background: #630031;
  border: none;
}

.reset-button {
  color: #cf4420;
  background: white;
  border: 1px solid #cf4420;
}
</style>
