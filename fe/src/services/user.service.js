class UserService {
  getData() {
    return localStorage.getItem("user");
  }
}
export default new UserService();
