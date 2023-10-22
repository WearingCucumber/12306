<template>
  <div>
    <a-button type="primary" @click = "showModal">新增</a-button>
    <a-modal v-model:open="open" title = "新增乘车人" @ok="handleOk" ok-text="添加" cancel-text="取消">
      <a-form :model="passenger" :label-col="{span: 4}" >
        <a-form-item label ="姓名:">
          <a-input v-model:value="passenger.name"/>
        </a-form-item>
        <a-form-item label="身份证号:">
          <a-input v-model:value="passenger.idCard"/>
        </a-form-item>
        <a-form-item label="乘客类型:">
          <a-select v-model:value="passenger.type">
            <a-select-option value="1">成人</a-select-option>
            <a-select-option value="2">儿童</a-select-option>
            <a-select-option value="3">学生</a-select-option>
          </a-select>
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>
<script>
import {defineComponent, reactive, ref} from "vue";
import axios from "axios";
import {notification} from "ant-design-vue";

export default defineComponent({


  setup(){
    const passenger=reactive({
      id:"",
      memberId:"",
      name:"",
      idCard:"",
      type:"",
      createTime:"",
      updateTime:""
    });
    const  open = ref(false);
    const showModal=()=>{
      open.value = true;
    };
    const handleOk = (e) =>{
      axios.post("/member/passenger/save",passenger).then(response=>{
        let data = response.data;
        if (data.success){
          notification.success({description:data.content})
          open.value=false;
        }else {
          notification.error({description:data.message})
        }
      })
      console.log(e)
      open.value = false;
    };
    return{
      passenger,
      open,
      showModal,
      handleOk

    }
  }
})
</script>