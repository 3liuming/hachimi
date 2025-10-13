import request from '@/utils/request'

// 查询领养申请信息列表
export function listApply(query) {
  return request({
    url: '/hachimiSys/apply/list',
    method: 'get',
    params: query
  })
}

// 查询领养申请信息详细
export function getApply(applyId) {
  return request({
    url: '/hachimiSys/apply/' + applyId,
    method: 'get'
  })
}

// 新增领养申请信息
export function addApply(data) {
  return request({
    url: '/hachimiSys/apply',
    method: 'post',
    data: data
  })
}

// 修改领养申请信息
export function updateApply(data) {
  return request({
    url: '/hachimiSys/apply',
    method: 'put',
    data: data
  })
}

// 删除领养申请信息
export function delApply(applyId) {
  return request({
    url: '/hachimiSys/apply/' + applyId,
    method: 'delete'
  })
}
