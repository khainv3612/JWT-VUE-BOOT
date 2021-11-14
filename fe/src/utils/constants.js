export const CONSTANTS = {
  API_URL: "http://localhost:8080/api/",
  REGEX_EMAIL:
    /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/,
  REGEX_PASSWORD:
    /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{8,15}$/,
  // message
  USERNAME_ERR: "Username is from 5 to 15 characters",
  EMAIL_ERR: "Email is invalid format",
  PASSWORD_ERR:
    "Password between 8 to 15 characters, at least one lowercase letter, one uppercase letter, one numeric digit, and one special character",
  PASS_NOT_MATCH: "Repassword is not matched",
};
