import Vue from 'vue'
import Router from 'vue-router'
import { normalizeURL } from '@nuxt/ufo'
import { interopDefault } from './utils'
import scrollBehavior from './router.scrollBehavior.js'

const _ba5088c8 = () => interopDefault(import('..\\pages\\about.vue' /* webpackChunkName: "pages/about" */))
const _6f282952 = () => interopDefault(import('..\\pages\\lend\\index.vue' /* webpackChunkName: "pages/lend/index" */))
const _73c0720c = () => interopDefault(import('..\\pages\\user.vue' /* webpackChunkName: "pages/user" */))
const _09c88614 = () => interopDefault(import('..\\pages\\user\\index.vue' /* webpackChunkName: "pages/user/index" */))
const _74bcf45e = () => interopDefault(import('..\\pages\\user\\account.vue' /* webpackChunkName: "pages/user/account" */))
const _7ae0d33e = () => interopDefault(import('..\\pages\\index.vue' /* webpackChunkName: "pages/index" */))

// TODO: remove in Nuxt 3
const emptyFn = () => {}
const originalPush = Router.prototype.push
Router.prototype.push = function push (location, onComplete = emptyFn, onAbort) {
  return originalPush.call(this, location, onComplete, onAbort)
}

Vue.use(Router)

export const routerOptions = {
  mode: 'history',
  base: '/',
  linkActiveClass: 'nuxt-link-active',
  linkExactActiveClass: 'nuxt-link-exact-active',
  scrollBehavior,

  routes: [{
    path: "/about",
    component: _ba5088c8,
    name: "about"
  }, {
    path: "/lend",
    component: _6f282952,
    name: "lend"
  }, {
    path: "/user",
    component: _73c0720c,
    children: [{
      path: "",
      component: _09c88614,
      name: "user"
    }, {
      path: "account",
      component: _74bcf45e,
      name: "user-account"
    }]
  }, {
    path: "/",
    component: _7ae0d33e,
    name: "index"
  }],

  fallback: false
}

function decodeObj(obj) {
  for (const key in obj) {
    if (typeof obj[key] === 'string') {
      obj[key] = decodeURIComponent(obj[key])
    }
  }
}

export function createRouter () {
  const router = new Router(routerOptions)

  const resolve = router.resolve.bind(router)
  router.resolve = (to, current, append) => {
    if (typeof to === 'string') {
      to = normalizeURL(to)
    }
    const r = resolve(to, current, append)
    if (r && r.resolved && r.resolved.query) {
      decodeObj(r.resolved.query)
    }
    return r
  }

  return router
}
