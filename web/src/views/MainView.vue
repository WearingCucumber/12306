<template>
  <a-layout id="components-layout-demo-top-side-2">
  <header-view/>
    <a-layout>
     <sider-component/>
        <a-layout-content
            :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
        >
          <router-view></router-view>

        </a-layout-content>

    </a-layout>
  </a-layout>
</template>
<script>

import { defineComponent } from 'vue';
import HeaderView from "@/components/headerComponent.vue";
import SiderComponent from "@/components/siderComponent.vue";
export default defineComponent({
  components: {
    SiderComponent,
    HeaderView
  },
  setup() {
    return {};
  }
});
</script>
<style>
#components-layout-demo-top-side-2 .logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl #components-layout-demo-top-side-2 .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

.site-layout-background {
  background: #fff;
}
</style>