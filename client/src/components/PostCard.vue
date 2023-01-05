<template>
  <li class="post-box">
    <div class="car-image">
      <img
        v-if="type === 0"
        src="https://cdn.pixabay.com/photo/2017/08/06/06/21/people-2589443_960_720.jpg"
        alt="car pic"
        width="192"
        height="108"
      />
      <img
        v-else
        src="https://cdn.pixabay.com/photo/2017/08/06/10/18/people-2590992_960_720.jpg"
        alt="passenger pic"
        width="192"
        height="108"
      />
      <div class="basic-info">
        <span class="user-info">
          {{ username
          }}<span class="user-type">{{
            type === 0 ? "(Driver)" : "(Passenger)"
          }}</span></span
        >
        <div class="price-detail">
          <div class="post-price">${{ price }}</div>
          <el-button
            class="detail-button"
            type="primary"
            color="#cf4420"
            :icon="More"
            @click="$router.push('/post/' + id)"
          ></el-button>
        </div>
      </div>
    </div>
    <div class="post-info">
      <div>
        <span class="location">{{ spotNameStart }}</span
        ><span> - </span>
        <span class="location">{{ spotNameEnd }}</span>
      </div>
      <div class="datetime">
        <span>{{ customDateFormat(startTime) }}</span> -
        <span>{{ customDateFormat(endTime) }}</span>
      </div>
      <div class="datetime">
        last updated: {{ customDateFormat(updateTime) }}
      </div>
      <div class="status">
        {{ status === 0 ? "opened" : status === 1 ? "completed" : "cancelled" }}
      </div>
    </div>
  </li>
</template>

<script>
import { More } from "@element-plus/icons-vue";

export default {
  props: {
    id: Number,
    userId: Number,
    username: String,
    updateTime: String,
    startTime: String,
    endTime: String,
    areaNameStart: String,
    areaNameEnd: String,
    spotNameStart: String,
    spotNameEnd: String,
    price: Number,
    status: Number,
    type: Number,
  },
  data() {
    return {
      More: More,
    };
  },
  methods: {
    customDateFormat(dateStr) {
      return new Date(dateStr)
        .toISOString()
        .replace(/T/g, " ")
        .replace(/\.[\d]{3}Z/, "");
    },
  },
};
</script>

<style scoped>
.car-image {
  display: flex;
  flex-direction: row;
}

.car-image img {
  width: 180px;
}

.basic-info {
  display: flex;
  flex-direction: column;
  padding-left: 20px;
  justify-content: space-between;
}

.user-info {
  font-size: 16px;
}

.user-type {
  color: gray;
  margin-left: 10px;
}

.price-detail {
  display: flex;
  flex-direction: row;
}

.post-price {
  font-size: 16px;
  color: #57575c;
  padding-top: 3px;
  margin-right: 80px;
}

.detail-button {
  height: 30px;
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

.post-info {
  margin-top: 10px;
  display: flex;
  flex-direction: column;
}

.location {
  font-size: 16px;
  color: #57575c;
}

.datetime {
  font-size: 14px;
  color: #57575c;
}

.status {
  font-size: 14px;
  color: gray;
}

.post-box {
  display: flex;
  flex-direction: column;
  width: 400px;
  background-color: #f2f5fa;
  border-radius: 10px;
  border: 1px solid #57575c;
  padding: 1em;
  gap: 0.25em;
}
</style>
