<template class="app-header">
  <el-menu
    :default-active="'1'"
    class="el-menu-demo"
    mode="horizontal"
    background-color="#CF4420"
    text-color="#fff"
    active-text-color="#ffd04b"
    :ellipsis="false"
    @select="handleSelect"
  >
    <el-menu-item index="1" class="logo">
      <template #title>
        <img src="@/assets/images/hokie.png" alt="hokie" class="logo" />
        <span class="logo-text">RideNShare</span>
      </template>
    </el-menu-item>
    <div class="flex-grow" />
    <el-menu-item index="2" v-if="user.uid">
      <img src="@/assets/images/message.png" class="message" alt="message" />
      <span>MESSAGES</span>
    </el-menu-item>
    <el-sub-menu index="3" v-if="user.uid">
      <template #title
        ><img
          src="@/assets/images/profile.png"
          class="profile"
          alt="profile"
        />{{ user.username }}</template
      >
      <el-menu-item index="3-1">PROFILE</el-menu-item>
      <el-menu-item index="3-2">POSTS</el-menu-item>
      <el-menu-item index="3-3">ORDERS</el-menu-item>
      <el-menu-item index="3-4">LOG OUT</el-menu-item>
    </el-sub-menu>
  </el-menu>
</template>

<script>
import router from "@/router";

export default {
  components: {},
  props: {},
  data() {
    return {
      user: {
        username: "",
        uid: null,
      },
    };
  },
  mounted() {
    if (sessionStorage.getItem("userInfo")) {
      // store user info into sessionStorage
      let temp = JSON.parse(sessionStorage.getItem("userInfo"));
      this.user.uid = temp["id"];
      this.user.username = temp["username"];
    }
  },
  methods: {
    handleSelect(key, keyPath) {
      if (key === "1") {
        router.push("/explore");
      } else if (key === "2") {
        router.push("/chat");
      } else if (key === "3-1") {
        router.push("/user");
      } else if (key === "3-2") {
        router.push("/posts");
      } else if (key === "3-3") {
        router.push("/order");
      } else if (key === "3-4") {
        this.logoutImpl();
      }
    },
    logoutImpl() {
      sessionStorage.removeItem("userInfo");
      this.user.uid = null;
      this.user.username = "";
      this.$message({
        message: "Log out success.",
        duration: 1500,
      });
      router.push("/");
    },
  },
};
</script>

<style scoped>
.app-header {
  position: sticky;
  top: 0;
}

.flex-grow {
  flex-grow: 1;
}

.logo {
  height: 50px;
}

.logo-text {
  margin-left: 5px;
  font-family: Comic Sans MS;
}

ul {
  align-items: center;
  justify-content: center;
}

.message,
.profile {
  height: 20px;
  margin: 0 10px;
}
</style>
