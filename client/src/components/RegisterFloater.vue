<template>
  <div
    class="me-login-box me-login-box-radius first-page"
    v-if="data.firstPage"
  >
    <h1>CREATE ACCOUNT</h1>
    <h6>RideNShare</h6>
    <el-form>
      <el-form-item prop="account">
        <el-input type="email" placeholder="VT email" v-model="form.uemail">
        </el-input>
      </el-form-item>

      <el-form-item prop="password">
        <el-input
          placeholder="password"
          type="password"
          v-model="form.password"
        ></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          placeholder="Please enter your password again"
          type="password"
          v-model="form.rePassword"
        ></el-input>
      </el-form-item>

      <el-button
        class="btn btn-primary btn-block mb-4 signup-button"
        type="primary"
        id="signup-button"
        :loading="data.loading"
        @click="submitForm()"
        >sign up</el-button
      >
    </el-form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "RegisterFloater",
  data() {
    return {
      form: {
        uemail: "",
        password: "",
        rePassword: "",
        question: "",
        answer: "",
      },
      data: {
        firstPage: true,
        loading: false,
      },
    };
  },
  methods: {
    submitForm() {
      var validate = /^([a-zA-Z0-9_-])+@vt.edu/;
      if (!validate.test(this.form.uemail)) {
        this.$alert("NOT a VT email!", "Error", {
          confirmButtonText: "OK",
        });
        this.form.uemail = "";
        this.form.password = "";
        this.form.rePassword = "";
      } else if (this.form.password.length < 6) {
        this.$alert("Password should be at least 6 characters.", "Error", {
          confirmButtonText: "OK",
        });
        this.form.password = "";
      } else if (this.form.password !== this.form.rePassword) {
        this.$alert(
          "The passwords entered are inconsistent, please check.",
          "Error",
          {
            confirmButtonText: "OK",
          }
        );
      } else {
        const data = {
          password: this.form.password,
          email: this.form.uemail,
          createTime: Date.now(),
          updateTime: Date.now(),
        };
        axios.post("sso/register", data).then((res) => {
          this.data.loading = true;
          if (res.data["activateStatus"] === 1) {
            this.$alert(
              "The account is existing. Please use it to log in.",
              "Error",
              {
                confirmButtonText: "OK",
              }
            );
          } else {
            this.$alert(
              "Sign up Success! Please go to your email to verify the email.",
              "Success",
              {
                confirmButtonText: "OK",
              }
            );
            this.$router.push("/login");
          }
          this.data.loading = false;
        });
      }
    },
  },
};
</script>

<style scoped>
#login {
  min-width: 100%;
  min-height: 100%;
}

.me-video-player {
  background-color: transparent;
  width: 100%;
  height: 100%;
  object-fit: fill;
  display: block;
  position: absolute;
  left: 0;
  z-index: 0;
  top: 0;
}

.first-page h1 {
  font-weight: bold;
}

.me-login-box {
  width: inherit;
  height: inherit;
  background-color: white;
  /*margin-top: 150px;*/
  margin-bottom: 150px;
  /*left: 50%;*/
  padding: 40px;
  /*position: fixed;*/
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
.me-login-box h6 {
  text-align: center;
  margin-bottom: 20px;
  vertical-align: middle;
}
.me-login-design {
  text-align: center;
  font-family: "Open Sans", sans-serif;
  font-size: 18px;
}

.me-login-design-color {
  color: #5fb878 !important;
}
.signup-button {
}
.me-login-button {
  text-align: center;
}

.me-login-button button {
  width: 100%;
}
</style>
