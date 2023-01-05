<template>
  <div class="center bias" id="login" v-title data-title="login - RideNShare">
    <login-floater></login-floater>
  </div>
</template>

<script>
import LoginFloater from "@/components/LoginFloater";

export default {
  name: "LoginView.vue",
  components: {
    LoginFloater,
  },
};
</script>

<style scoped>
@import "~mdb-ui-kit/css/mdb.min.css";
#login {
  min-width: 100%;
  min-height: 100%;
}

.bias {
  margin-top: -60px;
}
.me-login-box {
  width: 400px;
  height: 260px;
  background-color: white;
  margin-top: 150px;
  margin-bottom: 200px;
  left: 50%;
  padding: 30px;
}

.me-login-box-radius {
  border-radius: 10px;
  box-shadow: 0px 0px 1px 1px rgba(161, 159, 159, 0.1);
}

.me-login-box h1 {
  text-align: center;
  font-size: 24px;
  margin-bottom: 20px;
  vertical-align: middle;
}

.me-login-button button {
  width: 100%;
}
</style>
