<template>
  <div>
    <p><a-button type="primary" @click = "onAdd">新增</a-button></p>
    <a-table :data-source="passengers" :columns="columns" :pagination="pagination" @change="handleTableChange" :loading="loading">
      <template #bodyCell="{column,record}">
        <template v-if="column.dataIndex=== 'operation'">
          <a-space>
            <a @click = "onEdit(record)">编辑</a>
            <a-popconfirm
             title="确认删除?"
             @confirm="onDelete(record.id)"
             ok-text="确认" cancel-text="取消"
            >
              <a >删除</a>
            </a-popconfirm>
          </a-space>
        </template>
        <template v-if="column.dataIndex === 'type'">
          <span v-for="item in PASSENGER_TYPE_LIST" :key="item.key">
            <span v-if="item.key===record.type">
              {{item.value}}
            </span>
          </span>
        </template>
      </template>
    </a-table>
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
            <a-select-option v-for="item in PASSENGER_TYPE_LIST" :key="item.key" :value="item.key" >{{item.value}}</a-select-option>
          </a-select>
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>
<script>
import {defineComponent, onMounted, ref} from "vue";
import axios from "axios";
import {notification} from "ant-design-vue";

export default defineComponent({


  setup(){
    const PASSENGER_TYPE_LIST= window.PASSENGER_TYPE_LIST;//[{key:"1",value:"成人"},{key:"2",value:"儿童"},{key:"3",value:"学生"}]
    const passenger=ref({
      id:"",
      memberId:"",
      name:"",
      idCard:"",
      type:"",
      createTime:"",
      updateTime:""
    });
    const  open = ref(false);
    const onAdd=()=>{
      passenger.value={};
      open.value = true;
    };
    const onEdit = (record)=>{
      passenger.value=window.Tool.copy(record);
      open.value=true;
    }
    const  onDelete=(id)=>{
      axios.delete("/member/passenger/delete/"+id).then(response=>{
        handleQuery({
          page:1,
          pageSize: 2
        })
        let data = response.data;
        if (data.success){
          notification.success({description:data.content})
        }else {
          notification.error({description:data.message})
        }
      })
    }
    const handleOk = (e) =>{
      axios.post("/member/passenger/save",passenger.value).then(response=>{
        let data = response.data;
        if (data.success){
          notification.success({description:data.content})
          open.value=false;
          handleQuery({
            page:pagination.value.current,
            pageSize:pagination.value.pageSize
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
    }, {
      title:"更新时间",
      dataIndex: 'updateTime',
      key: 'updateTime'
      },{
      title:"操作",
      dataIndex: "operation"
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
          pagination.value.current = param.page;
          pagination.value.total = data.content.total;
        }else {
          notification.error({description:data.message})
        }
      })
    };
    const pagination = ref({
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
        pageSize:pagination.value.pageSize
      })
    });

    return{
      PASSENGER_TYPE_LIST,
      loading,
      handleTableChange,
      pagination,
      passengers,
      handleQuery,
      columns,
      passenger,
      open,
      onAdd,
      handleOk,
      onEdit,
      onDelete
    }
  }
})
</script>