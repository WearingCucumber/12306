<template>
  <div>
    <p><a-button type="primary" @click = "showModal">新增</a-button></p>
    <a-table :data-source="passengers" :columns="columns" :pagination="pagination" @change="handleTableChange" :loading="loading"/>
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
import {defineComponent, onMounted, reactive, ref} from "vue";
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
          handleQuery({
            page:1,
            pageSize:2
          })
        }else {
          notification.error({description:data.message})
        }
      })
      console.log(e)
      open.value = false;
    };
    const passengers = ref([]);
    const columns = [{
      title: '姓名',
      dataIndex:'name',
      key:'name'
    },{
      title: '身份证',
      dataIndex:'idCard',
      key:'idCard'
    },{
      title: '类型',
      dataIndex:'type',
      key:'type'
    },{
      title:"创建时间",
      dataIndex:'createTime',
      key:'createTime'
    }];
    const  handleQuery = (param)=>{
      loading.value=true;
      axios.get("/member/passenger/query-list",{
        params:{
          page:param.page,
          pageSize:param.pageSize
        }
      }).then((response)=>{
        loading.value=false;
        let data = response.data;
        if (data.success){
          passengers.value = data.content.list;
          //设置分页控件的值
          pagination.current = param.page;
          pagination.total = data.content.total;
        }else {
          notification.error({description:data.message})
        }
      })
    };
    const pagination = reactive({
      total:0,
      current:1,
      pageSize:2
    });
    const handleTableChange =(pagination)=>{
      handleQuery({
        page:pagination.current,
        pageSize:pagination.pageSize
      })
    };
    const loading = ref(false)
    onMounted(()=>{
      handleQuery({
        page:1,
        pageSize:pagination.pageSize
      })
    });

    return{
      loading,
      handleTableChange,
      pagination,
      passengers,
      handleQuery,
      columns,
      passenger,
      open,
      showModal,
      handleOk
    }
  }
})
</script>