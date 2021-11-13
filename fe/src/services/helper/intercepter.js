import axios from "axios";
import { CONSTANTS } from "../../utils/constants";

import { accountService } from "../user.service";

export function jwtInterceptor() {
  axios.interceptors.request.use((request) => {
    // add auth header with jwt if account is logged in and request is to the api url
    const account = accountService.getData();
    const isLoggedIn = account?.token;
    const isApiUrl = request.url.startsWith(CONSTANTS.API_URL);

    if (isLoggedIn && isApiUrl) {
      request.headers.common.Authorization = `Bearer ${account.token}`;
    }

    return request;
  });
}
