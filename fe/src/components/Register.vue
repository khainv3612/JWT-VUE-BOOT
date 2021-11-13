<template>
  <div class="main">
    <!-- Sign up form -->
    <section class="signup">
      <div class="container">
        <div class="signup-content">
          <div class="signup-form">
            <h2 class="form-title">Sign up</h2>
            <form
              @submit.prevent="handleRegister"
              class="register-form"
              id="register-form"
            >
              <div class="form-group">
                <label for="name"
                  ><i class="zmdi zmdi-account material-icons-name"></i
                ></label>
                <input
                  type="text"
                  name="name"
                  v-model="user.username"
                  id="name"
                  placeholder="Your Name"
                />
              </div>
              <div class="form-group">
                <label for="email"><i class="zmdi zmdi-email"></i></label>
                <input
                  type="email"
                  v-model="user.email"
                  name="email"
                  id="email"
                  placeholder="Your Email"
                />
              </div>
              <div class="form-group">
                <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                <input
                  type="password"
                  v-model="user.password"
                  name="pass"
                  id="pass"
                  placeholder="Password"
                />
              </div>
              <div class="form-group">
                <label for="re-pass"
                  ><i class="zmdi zmdi-lock-outline"></i
                ></label>
                <input
                  type="password"
                  name="re_pass"
                  id="re_pass"
                  placeholder="Repeat your password"
                />
              </div>
              <div class="form-group">
                <input
                  type="checkbox"
                  name="agree-term"
                  id="agree-term"
                  class="agree-term"
                />
                <label for="agree-term" class="label-agree-term"
                  ><span><span></span></span>I agree all statements in
                  <a href="#" class="term-service">Terms of service</a></label
                >
              </div>
              <div class="form-group form-button">
                <input
                  type="submit"
                  name="signup"
                  id="signup"
                  class="form-submit"
                  value="Register"
                />
              </div>
            </form>
          </div>
          <div class="signup-image">
            <figure>
              <img
                src="../assets/images/signup-image.jpg"
                alt="sing up image"
              />
            </figure>
            <a href="#" class="signup-image-link">I am already member</a>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import User from "../models/user";

export default {
  name: "register-comp",
  data() {
    return {
      user: new User(null, "", "", ""),
      submitted: false,
      successful: false,
      message: "",
    };
  },
  computed: {
    isLogin() {
      return this.$store.state.auth.status.loggedIn;
    },
  },

  methods: {
    handleRegister() {
      this.message = "";
      this.submitted = true;
      this.$validator.validate().then((isValid) => {
        if (isValid) {
          this.$store.dispatch("auth/signup", this.user).then(
            (data) => {
              this.message = data.message;
              this.successful = true;
            },
            (error) => {
              this.message =
                (error.response &&
                  error.response.data &&
                  error.response.data.message) ||
                error.message ||
                error.toString();
              this.successful = false;
            }
          );
        }
      });
    },
  },
  mounted() {
    if (this.isLogin) {
      this.$router.push("/");
    }
  },
};
</script>

<style></style>
