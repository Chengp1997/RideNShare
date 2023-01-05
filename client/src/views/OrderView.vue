<template>
  <div>
    <div class="orderDashboard">
      <div class="orderContainer">
        <template v-for="order in orderList" :key="order.id">
          <div class="orderBox">
            <div class="orderInfo">
              <div class="profile-card-6">
                <img
                  src="@/assets/images/orderBackground1.png"
                  class="img img-responsive"
                  width="290"
                  heigh="300"
                />
                <div class="profile-name">
                  No. {{ order.id }} <br />{{ order.createTime }}
                </div>
                <div class="profile-overview">
                  <div class="profile-overview">
                    <div class="row text-center">
                      <div class="col-xs-4">
                        <el-button
                          color="#cf4420"
                          class="detail-button"
                          type="primary"
                          @click="
                            $router.push(
                              '/orderDetail/' + order.postId + '*' + flag
                            )
                          "
                          >View Order
                        </el-button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </template>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { apiUrl } from "@/services/ApiService";
import { ref } from "vue";
import router from "@/router";

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
  name: "OrderView",

  data() {
    return {
      user: {
        email: "",
        uid: null,
      },
      flag: "true",
      orderList: [],
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
    };
  },

  created() {
    let temp = JSON.parse(sessionStorage.getItem("userInfo"));
    this.user.uid = temp["id"];
    this.getOrderList();
  },

  methods: {
    getOrderList() {
      axios.get(apiUrl + "order/orders/" + this.user.uid).then((res) => {
        this.orderList = res.data.content;

        this.orderList.forEach((item) => {
          item.createTime = item.createTime.replace("T", " ");
          item.createTime = item.createTime.substring(0, 19);
        });
      });
    },
  },
};
</script>

<style scoped>
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
.orderDashboard {
  /*margin-left: 200px;*/
  margin-left: 15%;
  margin-right: 15%;
  display: flex;
  /*flex-direction: row;*/
  align-items: center;
  justify-content: center;
}

.location {
  font-weight: bold;
  color: #57575c;
}

.profile-card-6 {
  background-color: #fff;
  border-radius: 5px;
  box-shadow: 0px 0px 25px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  position: relative;
  margin: 10px auto;
  cursor: pointer;
}

.profile-card-6 img {
  transition: all 0.15s linear;
}

.profile-card-6 .profile-name {
  position: absolute;
  top: 10px;
  left: 10px;
  font-size: 25px;
  font-weight: bold;
  color: #fff;
  padding: 15px 20px;
  background: linear-gradient(
    140deg,
    rgba(0, 0, 0, 0.4) 50%,
    rgba(255, 255, 0, 0) 50%
  );
  transition: all 0.15s linear;
}

.profile-card-6 .profile-position {
  position: absolute;
  color: rgba(255, 255, 255, 0.4);
  left: 30px;
  top: 100px;
  transition: all 0.15s linear;
}

.profile-card-6 .profile-overview {
  position: absolute;
  bottom: 0px;
  left: 0px;
  right: 0px;
  background: linear-gradient(
    0deg,
    rgba(0, 0, 0, 0.4) 50%,
    rgba(255, 255, 0, 0)
  );
  color: #fff;
  padding: 50px 0px 20px 0px;
  transition: all 0.15s linear;
}

.profile-card-6 .profile-overview h3 {
  font-weight: bold;
}

.profile-card-6 .profile-overview p {
  color: rgba(255, 255, 255, 0.7);
}

.profile-card-6:hover img {
  filter: brightness(80%);
}

.profile-card-6:hover .profile-name {
  padding-left: 25px;
  padding-top: 20px;
}

.profile-card-6:hover .profile-position {
  left: 40px;
}

.profile-card-6:hover .profile-overview {
  padding-bottom: 25px;
}

/*.orderInfo {*/
/*  display: flex;*/
/*  flex-direction: column;*/
/*  align-content: center;*/
/*}*/

.orderBox {
  height: 500px;
  width: 290px;
  /*display: flex;*/
  /*flex-direction: column;*/
  /*align-items: center;*/
  /*justify-content: center;*/
  margin: 30px;
  border-radius: 120px;
  /*border: 2px solid #eaeaea;*/
  /*padding: 1em;*/
}

.orderContainer {
  display: flex;
  justify-content: left;
  align-items: center;
  flex-wrap: wrap;
}
</style>
