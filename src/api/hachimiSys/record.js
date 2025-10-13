import request from '@/utils/request'

// 查询宠物健康档案信息列表
export function listRecord(query) {
  return request({
    url: '/hachimiSys/record/list',
    method: 'get',
    params: query
  })
}

// 查询宠物健康档案信息详细
export function getRecord(healthId) {
  return request({
    url: '/hachimiSys/record/' + healthId,
    method: 'get'
  })
}

// 新增宠物健康档案信息
export function addRecord(data) {
  return request({
    url: '/hachimiSys/record',
    method: 'post',
    data: data
  })
}

// 修改宠物健康档案信息
export function updateRecord(data) {
  return request({
    url: '/hachimiSys/record',
    method: 'put',
    data: data
  })
}

// 删除宠物健康档案信息
export function delRecord(healthId) {
  return request({
    url: '/hachimiSys/record/' + healthId,
    method: 'delete'
  })
}
