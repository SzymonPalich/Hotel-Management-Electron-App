/* eslint-disable */
declare module '*.vue' {
  import type { DefineComponent } from 'vue'
  const component: DefineComponent<{}, {}, any>
  export default component
}
declare module 'vue/types/vue' {
  interface Vue {
    $router: VueRouter
  }
}
declare module "*.json" {
  const value: any;
  export default value;
}
declare module 'vue-select'{}