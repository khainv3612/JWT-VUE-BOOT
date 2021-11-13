<template>
  <div class="container">
    <h3>Hello {{ getCurrentUser.username }}</h3>
  </div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      // currentUser: this.getCurrentUser(),
    };
  },
  computed: {
    getCurrentUser() {
      return this.$store.state.auth.user;
    },
  },
  mounted() {
    if (!this.getCurrentUser) {
      this.$router.push("/login");
    }
  },
};
</script>
