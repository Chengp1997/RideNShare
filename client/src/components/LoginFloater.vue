<template>
  <div class="me-login-box me-login-box-radius">
    <h1>RideNShare</h1>

    <el-form>
      <!-- Email input -->
      <div class="form-outline mb-4">
        <el-input
          type="email"
          id="form2Example1"
          class="form-control"
          v-model="form.inputEmail"
          placeholder="Email address"
        />
      </div>

      <!-- Password input -->
      <div class="form-outline mb-4">
        <el-input
          type="password"
          id="form2Example2"
          class="form-control"
          v-model="form.password"
          placeholder="Password"
        />
      </div>

      <!-- Submit button -->
      <el-button
        type="button"
        class="btn btn-primary btn-block mb-4"
        @click="submitAndCheck()"
      >
        Sign in
      </el-button>

      <!-- Register buttons -->
      <div class="text-center">
        <p>Not a member? <a href="/signup">Register</a></p>
      </div>
    </el-form>
  </div>
</template>

<script>
import axios from "axios"; // module

export default {
  name: "LoginFloater",
  data() {
    return {
      form: {
        inputEmail: "",
        password: "",
      },
    };
  },
  methods: {
    submitAndCheck() {
      const path = "sso/login";
      const data = {
        email: this.form.inputEmail,
        password: this.form.password,
      };

      let myParams = new URLSearchParams();
      myParams.append("email", data.email);
      myParams.append("password", data.password);

      axios
        .post(path, myParams)
        .then((response) => {
          if (response.data !== "") {
            let user = JSON.stringify(response.data);
            if (response.data["activateStatus"] === 0) {
              this.$alert(
                "Your account has not been activated. Please turn to your email to activate.",
                "Error",
                {
                  confirmButtonText: "OK",
                }
              );
            } else {
              sessionStorage.setItem("userInfo", user);
              this.$message({
                message: "Success",
                type: "success",
                duration: 1500,
              });
              this.$router.push("/explore");
            }
          } else {
            this.form.inputEmail = "";
            this.form.password = "";
            this.$alert("Please check your account or password!", "Error", {
              confirmButtonText: "OK",
            });
          }
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
};
</script>

<style scoped>
@import "~mdb-ui-kit/css/mdb.min.css";
#login {
  min-width: 100%;
  min-height: 100%;
}

.me-login-box {
  width: 400px;
  height: 260px;
  background-color: white;
  margin-bottom: 200px;
  left: 50%;
  padding: 30px;
}

.me-login-box-radius {
  border-radius: 10px;
  box-shadow: 0 0 1px 1px rgba(161, 159, 159, 0.1);
}

.me-login-box h1 {
  text-align: center;
  font-size: 24px;
  margin-bottom: 20px;
  vertical-align: middle;
  font-family: "Times New Roman", Times, serif;
  font-weight: bold;
}
.me-login-box p {
  font-family: "Times New Roman", Times, serif;
}
.me-login-design {
  text-align: center;
  font-family: "Open Sans", sans-serif;
  font-size: 18px;
}

.me-login-design-color {
  color: #5fb878 !important;
}

.me-login-button {
  text-align: center;
}

.me-login-button button {
  width: 100%;
}
</style>
