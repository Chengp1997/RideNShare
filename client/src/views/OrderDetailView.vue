<template>
  <section class="h-100 h-custom" style="background-color: #eee">
    <div class="container py-5 h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-lg-8 col-xl-6">
          <div
            class="card border-top border-bottom border-3"
            style="border-color: #f37a27"
          >
            <div class="card-body p-5">
              <p class="lead fw-bold mb-5" style="color: #f37a27">
                Order Detail
              </p>

              <div class="row">
                <div class="col mb-3">
                  <p>Post No:</p>
                </div>
                <div class="col mb-3">
                  <p>{{ this.postVo.id }}</p>
                </div>
              </div>

              <div class="row">
                <div class="col mb-3">
                  <p>Order Create Time:</p>
                </div>
                <div class="col mb-3">
                  <p>{{ Date() }}</p>
                </div>
              </div>

              <div class="row">
                <div class="col mb-3">
                  <p>Driver ID:</p>
                </div>
                <div class="col mb-3">
                  <p>{{ this.getDriverId() }}</p>
                </div>
              </div>

              <div class="row">
                <div class="col mb-3">
                  <p>Passenger ID:</p>
                </div>
                <div class="col mb-3">
                  <p>{{ this.getPassengerId() }}</p>
                </div>
              </div>

              <div class="row">
                <div class="col mb-3">
                  <p>Start Time:</p>
                </div>
                <div class="col mb-3">
                  <p>{{ this.postVo.startTime }}</p>
                </div>
              </div>

              <div class="row">
                <div class="col mb-3">
                  <p>End Time:</p>
                </div>
                <div class="col mb-3">
                  <p>{{ this.postVo.endTime }}</p>
                </div>
              </div>

              <div class="row">
                <div class="col mb-3">
                  <p>Start Location:</p>
                </div>
                <div class="col mb-3">
                  <p>{{ this.postVo.spotNameStart }}</p>
                </div>
              </div>

              <div class="row">
                <div class="col mb-3">
                  <p>End Location:</p>
                </div>
                <div class="col mb-3">
                  <p>{{ this.postVo.spotNameEnd }}</p>
                </div>
              </div>

              <div class="row">
                <div class="col mb-3">
                  <p>Price:</p>
                </div>
                <div class="col mb-3">
                  <p>{{ this.postVo.price }}</p>
                </div>
              </div>
            </div>
            <div>
              <div class="buttonStyle" v-if="isFromOrder == 'true'">
                <el-button
                  color="#cf4420"
                  class="detail-button"
                  type="primary"
                  @click="$router.push('/order')"
                >
                  Finish
                </el-button>
              </div>
              <div class="buttonStyle">
                <el-button
                  color="#cf4420"
                  class="detail-button"
                  v-if="this.isFromOrder != 'true'"
                  type="primary"
                  @click="saveOrderClicked"
                >
                  Confirm
                </el-button>
                <el-button
                  color="#cf4420"
                  class="reset-button"
                  v-if="this.isFromOrder != 'true'"
                  @click="$router.push('/post/' + postVo.id)"
                  >Cancel
                </el-button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import axios from "axios";
import { apiUrl } from "@/services/ApiService";
import { useRoute } from "vue-router";

export default {
  name: "OrderDetail",
  data() {
    return {
      userId: Number,
      userNameLabel: String,
      postToOrderId: Number,
      isFromOrder: "false",
      currentUser: {
        id: String,
        username: String,
        email: String,
        phone: String,
      },
      orderVo: {
        id: Number,
        postId: Number,
        startTime: "",
        startLocation: "",
      },

      postVo: {
        id: Number,
        userId: Number,
        userName: "",
        updateTime: "",
        startTime: "",
        endTime: "",
        type: "",
      },

      order: {
        createTime: Date,
        updateTime: Date,
        driverId: Number,
        passengerId: Number,
        postId: Number,
        price: 0.0,
        status: 0,
        chatId: 0,
      },
      orderFormEdit: true,
      users: {
        driverId: 0,
        passengerId: 0,
      },
      currentUserType: 0, // 0: driver, 1: passenger
    };
  },
  created() {
    if (sessionStorage.getItem("userInfo")) {
      let temp = JSON.parse(sessionStorage.getItem("userInfo"));
      this.userId = temp["id"];
    }
    const route = useRoute();
    this.postToOrderId = route.params.id.split("*")[0];
    this.isFromOrder = route.params.id.split("*")[1];
    this.getPostVo();
    this.getUserVo();
  },

  methods: {
    getUserVo() {
      axios.get(apiUrl + "user/" + this.userId).then((res) => {
        this.currentUser = res.data;
        this.userNameLabel = this.currentUser.username;
      });
    },
    getPostVo() {
      axios.get(apiUrl + "post/" + this.postToOrderId).then((res) => {
        this.postVo = res.data;
      });
      this.postVo.startTime = this.postVo.startTime.replace("T", " ");
      this.currentUserType = this.postVo.type == 0 ? 1 : 0;
    },

    getTime() {
      return this.postVo.startTime.replace("T", " ");
    },
    saveOrderClicked() {
      this.isFromOrder = "true";
      this.createOrder();
      axios.post(apiUrl + "order/", this.order).then((res) => {
        this.orderVo = res.data;
      });
    },
    cancelOrderClicked() {
      this.orderFormEdit = false;
    },

    createOrder() {
      this.order.createTime = "2022-11-25 15:00:00";
      this.order.updateTime = "2022-11-25 15:00:00";
      console.log("User Type" + this.currentUserType);
      if (this.currentUserType == 0) {
        this.order.driverId = this.userId;
        this.order.passengerId = this.postVo.userId;
      } else {
        this.order.driverId = this.postVo.userId;
        this.order.passengerId = this.userId;
      }

      console.log("this.userID" + this.order.driverId);
      this.order.postId = this.postVo.id;
      this.order.price = this.postVo.price;
      this.order.status = this.postVo.status;
      this.order.chatId = 1;
    },

    getNow() {
      const today = new Date();
      const date =
        today.getFullYear() +
        "-" +
        (today.getMonth() + 1) +
        "-" +
        today.getDate();
      const time =
        today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
      const dateTime = date + " " + time;
      this.timestamp = dateTime;
    },

    getDriverId() {
      if (this.postVo.status == 0) {
        return this.postVo.userId;
      } else {
        return this.userId;
      }
    },

    getPassengerId() {
      if (this.postVo.status == 1) {
        return this.postVo.userId;
      } else {
        return this.userId;
      }
    },
  },
};
</script>

<style scoped>
.reset-button {
  height: 40px;
  font-size: 16px;
  color: #cf4420;
  background: white;
  border: 1px solid #cf4420;
}

.reset-button:hover {
  color: white;
  background: #630031;
  border: none;
}
.detail-button {
  height: 40px;
  font-size: 16px;
  text-align: center;
  border: none;
  background: #cf4420;
  color: white;
}

.detail-button:hover {
  background: #630031;
  border: none;
}
.orderDetailContainer {
  height: 100vh;
  /*background: #eaeaea;*/
}
.buttonStyle {
  display: flex;
  flex-direction: row;
  justify-content: center;
}
.order_detail_body {
  margin-left: 20%;
  margin-right: 20%;
  margin-bottom: 50px;
  padding-left: 50px;
  padding-right: 50px;
  font-size: 30px;
}
.info_card {
  margin-bottom: 20px;
}

@media (min-width: 1025px) {
  .h-custom {
    height: 100vh;
  }
}

.horizontal-timeline .items {
  border-top: 2px solid #ddd;
}

.horizontal-timeline .items .items-list {
  position: relative;
  margin-right: 0;
}

.horizontal-timeline .items .items-list:before {
  content: "";
  position: absolute;
  height: 8px;
  width: 8px;
  border-radius: 50%;
  background-color: #ddd;
  top: 0;
  margin-top: -5px;
}

.horizontal-timeline .items .items-list {
  padding-top: 15px;
}
</style>
