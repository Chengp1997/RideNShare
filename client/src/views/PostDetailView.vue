<template>
  <div class="post-page">
    <div class="post-form">
      <el-form ref="postVoForm" :model="postDetailVo" label-width="120px">
        <el-row>
          <el-form-item v-if="!postDetailVo.id || editing" label="pick-up area">
            <el-select
              v-model="postDetailVo.startSpot.areaId"
              placeholder="where to pick-up"
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
          <el-form-item v-if="!postDetailVo.id || editing" label="pick-up spot">
            <el-select
              v-model="postDetailVo.startSpot.id"
              placeholder="where to pick-up"
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
        </el-row>

        <el-row>
          <el-form-item
            v-if="!postDetailVo.id || editing"
            label="drop-off area"
          >
            <el-select
              v-model="postDetailVo.endSpot.areaId"
              placeholder="where to drop-off"
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
          <el-form-item
            v-if="!postDetailVo.id || editing"
            label="drop-off spot"
          >
            <el-select
              v-model="postDetailVo.endSpot.id"
              placeholder="where to drop-off"
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
        </el-row>

        <el-row>
          <el-form-item label="pick-up time">
            <el-date-picker
              :disabled="postDetailVo.id !== '' && !editing"
              v-model="postDetailVo.startTime"
              type="datetime"
              placeholder="when to pick-up"
            />
          </el-form-item>

          <el-form-item label="drop-off time">
            <el-date-picker
              :disabled="postDetailVo.id !== '' && !editing"
              v-model="postDetailVo.endTime"
              type="datetime"
              placeholder="when to drop-off"
          /></el-form-item>
        </el-row>

        <el-row>
          <el-form-item
            v-if="postDetailVo.id !== '' && !editing"
            label="pick-up spot"
          >
            <el-input
              :disabled="postDetailVo.id !== ''"
              v-model="postDetailVo.spotNameStart"
              placeholder=""
            />
          </el-form-item>
          <el-form-item
            v-if="postDetailVo.id !== '' && !editing"
            label="drop-off spot"
          >
            <el-input
              :disabled="postDetailVo.id !== ''"
              v-model="postDetailVo.spotNameEnd"
              placeholder=""
            />
          </el-form-item>
        </el-row>

        <el-row>
          <el-form-item label="role">
            <el-select
              :disabled="postDetailVo.id !== '' && !editing"
              v-model="postDetailVo.type"
              placeholder="Please select your role"
            >
              <template #prefix>
                <span style="padding-left: 5px">
                  <el-icon><User /></el-icon>
                </span>
              </template>
              <el-option label="Driver" :value="0">Driver</el-option>
              <el-option label="Passenger" :value="1">Passenger</el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="expected price">
            <el-input-number
              :disabled="postDetailVo.id !== '' && !editing"
              :precision="2"
              :step="0.1"
              v-model="postDetailVo.price"
            ></el-input-number>
          </el-form-item>
        </el-row>

        <el-row
          ><el-form-item v-if="postDetailVo.id !== ''" label="posted by:"
            ><span>{{ postDetailVo.username }}</span></el-form-item
          >
          <el-form-item v-if="postDetailVo.id !== ''" label="phone:"
            ><span>{{ postDetailVo.phone }}</span></el-form-item
          >
        </el-row>

        <el-row
          class="car-card"
          v-if="postDetailVo.id !== '' && postDetailVo.type === 0"
        >
          <el-card :body-style="{ padding: '0px' }">
            <img
              v-bind:src="postDetailVo.carVo.image"
              class="image"
              alt="car image"
            />
            <div style="padding: 14px">
              <span
                >{{ postDetailVo.carVo.years }}
                {{ postDetailVo.carVo.carType }}
              </span>
              <div class="bottom">
                <span class="car-info">
                  Vehicle registration plate:
                  {{ postDetailVo.carVo.plateNumber }}
                </span>
                <span class="car-info"
                  >capacity: {{ postDetailVo.carVo.capacity }}
                </span>
              </div>
            </div>
          </el-card>
        </el-row>

        <el-row class="button-group">
          <el-button
            color="#cf4420"
            v-if="!postDetailVo.id"
            class="create-button"
            type="primary"
            @click="addPost"
            >create
          </el-button>
          <div v-if="postDetailVo.userId === user.uid">
            <el-button
              color="#cf4420"
              v-if="postDetailVo.id && !editing"
              class="create-button"
              type="primary"
              @click="editing = true"
              >edit
            </el-button>
            <el-button
              color="#cf4420"
              v-if="editing"
              class="create-button"
              type="primary"
              @click="editPost"
              >save</el-button
            >
            <el-button
              color="#cf4420"
              v-if="editing"
              class="cancel-button"
              @click="editing = false"
              >cancel</el-button
            >
          </div>
          <div v-if="postDetailVo.userId !== user.uid">
            <el-button
              color="#cf4420"
              v-if="postDetailVo.id"
              class="create-button"
              type="primary"
              @click="$router.push('/orderDetail/' + postDetailVo.id)"
              >order
            </el-button>
            <el-button
              color="#cf4420"
              v-if="postDetailVo.id"
              class="create-button"
              type="primary"
              @click="
                $router.push(
                  '/chat?pid=' +
                    postDetailVo.userId +
                    '&pname=' +
                    postDetailVo.username
                )
              "
              >chat
            </el-button>
          </div>
        </el-row>
      </el-form>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { useRoute } from "vue-router";
import router from "@/router";
import { Location, User } from "@element-plus/icons-vue";

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

export default {
  name: "PostView",
  components: {
    Location,
    User,
  },
  data() {
    return {
      user: {
        email: "",
        uid: null,
      },
      areaList: [],
      startSpotList: [],
      endSpotList: [],
      isSelectVisible: false,
      postDetailVo: {
        id: "",
        userId: "",
        username: "",
        updateTime: "",
        startTime: "",
        endTime: "",
        spotNameStart: "",
        spotNameEnd: "",
        price: "",
        status: "",
        type: "",
        phone: "",
        carVo: {
          id: "",
          plateNumber: "",
          carType: "",
          capacity: "",
          years: "",
          image: "",
        },
        startSpot: {
          id: "",
          location: "",
          longitude: "",
          latitude: "",
          areaId: "",
          areaName: "",
        },
        endSpot: {
          id: "",
          location: "",
          longitude: "",
          latitude: "",
          areaId: "",
          areaName: "",
        },
      },
      editing: "",
    };
  },
  watch: {
    "postDetailVo.startSpot.areaId"(newVal, oldVal) {
      if (this.isSelectVisible) {
        this.postDetailVo.startSpot.id = "";
      }
      if (!isEmpty(newVal)) {
        this.getStartSpotList(newVal);
      }
    },
    "postDetailVo.endSpot.areaId"(newVal, oldVal) {
      if (this.isSelectVisible) {
        this.postDetailVo.endSpot.id = "";
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
  },
  created() {
    const route = useRoute();
    const id = route.params.id;
    this.editing = false;
    if (id !== "edit") {
      this.getPostDetailVo(id);
    }
    this.getAreaList();
  },
  methods: {
    getPostDetailVo(postId) {
      axios.get("post/" + postId).then((res) => {
        this.postDetailVo = res.data;
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
    addPost() {
      const url = "post/add";
      if (!this.validateData()) {
        return;
      }

      const data = {
        id: "",
        startTime: this.postDetailVo.startTime
          .toISOString()
          .replace(/T/g, " ")
          .replace(/\.[\d]{3}Z/, ""),
        endTime: this.postDetailVo.endTime
          .toISOString()
          .replace(/T/g, " ")
          .replace(/\.[\d]{3}Z/, ""),
        spotIdStart: this.postDetailVo.startSpot.id,
        spotIdEnd: this.postDetailVo.endSpot.id,
        price: this.postDetailVo.price,
        userId: this.user.uid,
        type: this.postDetailVo.type,
      };
      axios.post(url, data).then((res) => {
        this.postDetailVo = res.data;
        let postId = this.postDetailVo.id;
        let url = "/post/" + postId;
        router.push({ path: url });
      });
    },
    editPost() {
      const url = "post";
      const data = {
        id: this.postDetailVo.id,
        startTime: new Date(this.postDetailVo.startTime)
          .toISOString()
          .replace(/T/g, " ")
          .replace(/\.[\d]{3}Z/, ""),
        endTime: new Date(this.postDetailVo.endTime)
          .toISOString()
          .replace(/T/g, " ")
          .replace(/\.[\d]{3}Z/, ""),
        spotIdStart: this.postDetailVo.startSpot.id,
        spotIdEnd: this.postDetailVo.endSpot.id,
        spotNameStart: this.postDetailVo.spotNameStart,
        spotNameEnd: this.postDetailVo.spotNameEnd,
        price: this.postDetailVo.price,
        userId: this.user.uid,
        type: this.postDetailVo.type,
      };
      axios.put(url, data).then((res) => {
        this.postDetailVo = res.data;
        this.editing = false;
      });
    },
    validateData() {
      if (this.postDetailVo.startSpot.id === "") {
        alert("pick-up area and spot cannot be empty");
        return false;
      }
      if (this.postDetailVo.endSpot.id === "") {
        alert("drop-off area and spot cannot be empty");
        return false;
      }
      if (this.postDetailVo.startTime === "") {
        alert("pick-up time cannot be empty");
        return false;
      }
      if (this.postDetailVo.endTime === "") {
        alert("drop-off time cannot be empty");
        return false;
      }
      if (this.postDetailVo.type === "") {
        alert("please select your role");
        return false;
      }
      if (this.postDetailVo.price === "") {
        alert("price cannot be empty, enter 0 instead");
        return false;
      }

      return true;
    },
  },
};
</script>

<style scoped>
.post-page {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 40px;
  margin-bottom: 40px;
}

.post-form {
  margin: auto;
}

.create-button {
  height: 30px;
  font-size: 14px;
  text-align: center;
  border: none;
  background: #cf4420;
  color: white;
}

.create-button:hover,
.cancel-button:hover {
  color: white;
  background: #630031;
  border: none;
}

.cancel-button {
  color: #cf4420;
  background: white;
  border: 1px solid #cf4420;
}

.button-group {
  position: relative;
  margin-top: 20px;
  margin-left: 45%;
  margin-bottom: 40px;
}

.car-card {
  position: relative;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
}

.car-info {
  font-size: 14px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.image {
  width: 600px;
  display: block;
}
</style>
