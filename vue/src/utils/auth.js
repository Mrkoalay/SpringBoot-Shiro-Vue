import Cookies from 'js-cookie'

const LoginKey = '40370979-14cc-47a3-a6ab-bfa2bdb91e4c'

export function getToken() {
  return Cookies.get(LoginKey);
}

export function setToken(token) {
  return Cookies.set(LoginKey, token)
}

export function removeToken() {
  return Cookies.remove(LoginKey)
}
