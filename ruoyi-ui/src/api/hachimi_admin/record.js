import request from '@/utils/request'

// 查询宠物健康档案列表
export function listRecord(query) {
  return request({
    url: '/hachimi_admin/record/list',
    method: 'get',
    params: query
  })
}

// 查询宠物健康档案详细
export function getRecord(healthId) {
  return request({
    url: '/hachimi_admin/record/' + healthId,
    method: 'get'
  })
}

// 新增宠物健康档案
export function addRecord(data) {
  return request({
    url: '/hachimi_admin/record',
    method: 'post',
    data: data
  })
}

// 修改宠物健康档案
export function updateRecord(data) {
  return request({
    url: '/hachimi_admin/record',
    method: 'put',
    data: data
  })
}

// 删除宠物健康档案
export function delRecord(healthId) {
  return request({
    url: '/hachimi_admin/record/' + healthId,
    method: 'delete'
  })
}
