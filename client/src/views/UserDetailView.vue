<template>
  <el-container>
    <el-header class="center user_card_header">
      <div class="block">
        <el-avatar
          :size="100"
          src="https://ridenshare.s3.amazonaws.com/image/hello.jpeg"
        ></el-avatar>
      </div>
      <div class="block">
        <h1>{{ userNameLabel }}</h1>
        <span>id: {{ currentUser.id }}</span>
      </div>
    </el-header>
    <el-main class="user_card_body center">
      <el-main class="info_card">
        <el-container>
          <el-form
            :model="currentUser"
            label-width="100px"
            :disabled="!userFormEdit"
          >
            <h3>User Info</h3>
            <el-form-item label="User Name">
              <el-input v-model="currentUser.username"></el-input>
            </el-form-item>
            <el-form-item label="Email">
              <el-input :disabled="true" v-model="currentUser.email"></el-input>
            </el-form-item>
            <el-form-item label="Phone">
              <el-input v-model="currentUser.phone"></el-input>
            </el-form-item>
          </el-form>
          <img
            class="car_image center avatar"
            v-bind:src="carVo.image"
            style="visibility: hidden"
          />
        </el-container>
        <div>
          <el-button
            class="edit-button"
            v-if="!this.userFormEdit"
            type="primary"
            @click="editUserClicked"
          >
            Edit
          </el-button>
          <el-button
            class="edit-button"
            v-if="userFormEdit"
            type="primary"
            @click="saveUserClicked"
          >
            Save
          </el-button>
          <el-button
            class="cancel-button"
            v-if="userFormEdit"
            @click="cancelUserClicked"
          >
            Cancel
          </el-button>
        </div>
      </el-main>
      <el-main>
        <el-container class="info_card">
          <el-form :model="carVo" label-width="100px" :disabled="!carFormEdit">
            <h3>Car Info</h3>
            <el-form-item label="Car Plate">
              <el-input v-model="carVo.plateNumber"></el-input>
            </el-form-item>
            <el-form-item label="Car Type">
              <el-input v-model="carVo.carType"></el-input>
            </el-form-item>
            <el-form-item label="Capacity">
              <el-input v-model="carVo.capacity"></el-input>
            </el-form-item>
            <el-form-item label="Years">
              <el-input v-model="carVo.years"></el-input>
            </el-form-item>
          </el-form>
          <!--upload image-->
          <div>
            <div class="car_image_block center">
              <el-main>
                <div>
                  <el-upload
                    class="avatar-uploader"
                    action="upload"
                    list-type="picture-card"
                    :show-file-list="false"
                    :http-request="uploadImage"
                    :before-upload="beforeAvatarUpload"
                    :disabled="!carFormEdit"
                  >
                    <img
                      v-if="this.carVo.image"
                      :src="this.carVo.image"
                      class="avatar"
                    />
                    <el-button
                      v-if="this.carVo.image === ''"
                      size="small"
                      type="primary"
                      >upload Car Image</el-button
                    >
                  </el-upload>
                </div>
                <span
                  class="tip"
                  :style="{ visibility: carFormEdit ? 'visible' : 'hidden' }"
                  >Upload Car Image here</span
                >
              </el-main>
            </div>
          </div>
        </el-container>
        <div>
          <el-button
            class="edit-button"
            v-if="!carFormEdit"
            type="primary"
            @click="editCarClicked"
            >Edit</el-button
          >
          <el-button
            class="edit-button"
            v-if="carFormEdit"
            type="primary"
            @click="saveCarClicked"
          >
            Save
          </el-button>
          <el-button
            class="cancel-button"
            v-if="carFormEdit"
            @click="cancelCarClicked"
            >Cancel</el-button
          >
        </div>
      </el-main>
    </el-main>
  </el-container>
</template>

<script>
import axios from "axios";
export default {
  name: "UserDetail",
  data() {
    return {
      userId: Number,
      userNameLabel: String,
      currentUser: {
        id: String,
        username: String,
        email: String,
        phone: String,
      },
      carVo: {
        id: Number,
        plateNumber: "",
        carType: "",
        capacity: null,
        years: null,
        image: "",
      },
      userFormEdit: false,
      carFormEdit: false,

      uploadUrl: "upload/",
    };
  },
  created() {
    if (sessionStorage.getItem("userInfo")) {
      let temp = JSON.parse(sessionStorage.getItem("userInfo"));
      this.userId = temp["id"];
    }
    this.getCarVo();
    this.getUserVo();
  },
  methods: {
    getUserVo() {
      axios.get("user/" + this.userId).then((res) => {
        this.currentUser = res.data;
        this.userNameLabel = this.currentUser.username;
      });
    },
    getCarVo() {
      axios.get("carInfo/user/" + this.userId).then((res) => {
        this.carVo = res.data;
      });
    },
    editUserClicked() {
      this.userFormEdit = true;
    },
    saveUserClicked() {
      this.userFormEdit = false;
      axios.put("user/" + this.userId, this.currentUser).then((res) => {
        this.currentUser = res.data;
        this.userNameLabel = this.currentUser.username;
      });
    },
    cancelUserClicked() {
      this.userFormEdit = false;
    },
    editCarClicked() {
      this.carFormEdit = true;
    },
    saveCarClicked() {
      this.carFormEdit = false;
      if (this.carVo.id == null) {
        const data = {
          plateNo: this.carVo.plateNumber,
          userId: this.userId,
          carType: this.carVo.carType,
          capacity: this.carVo.capacity,
          years: this.carVo.years,
          image: this.carVo.image,
        };
        axios.post("carInfo/", data).then((res) => (this.carVo = res.data));
      } else {
        axios.put("carInfo/" + this.carVo.id, this.carVo).then((res) => {
          this.carVo = res.data;
        });
      }
    },
    cancelCarClicked() {
      this.carFormEdit = false;
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("Only JPG!");
      }
      if (!isLt2M) {
        this.$message.error("Limit 2MB!");
      }
      return isJPG && isLt2M;
    },
    uploadImage(params) {
      let formData = new FormData();
      formData.append("image", params.file);
      axios.post(params.action, formData).then((res) => {
        this.carVo.image = res.data;
      });
    },
  },
};
</script>

<style scoped>
.user_card_header {
  margin: 50px;
  padding-left: 50px;
  padding-right: 50px;
}
.user_card_body {
  margin-left: 20%;
  margin-right: 20%;
  margin-bottom: 50px;
  padding-left: 50px;
  padding-right: 50px;
  font-size: 30px;
}
.car_image_block {
  width: 300px;
  height: 200px;
  margin-top: 30px;
  margin-left: 200px;
}
.info_card {
  margin-bottom: 20px;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
.tip {
  font-size: 10px;
}

.edit-button {
  background: #cf4420;
  color: white;
  border: none;
}

.edit-button:hover,
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
</style>
