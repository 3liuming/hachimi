import request from '@/utils/request'

// 查询领养后反馈信息列表
export function listFeedback(query) {
  return request({
    url: '/hachimiSys/feedback/list',
    method: 'get',
    params: query
  })
}

// 查询领养后反馈信息详细
export function getFeedback(feedbackId) {
  return request({
    url: '/hachimiSys/feedback/' + feedbackId,
    method: 'get'
  })
}

// 新增领养后反馈信息
export function addFeedback(data) {
  return request({
    url: '/hachimiSys/feedback',
    method: 'post',
    data: data
  })
}

// 修改领养后反馈信息
export function updateFeedback(data) {
  return request({
    url: '/hachimiSys/feedback',
    method: 'put',
    data: data
  })
}

// 删除领养后反馈信息
export function delFeedback(feedbackId) {
  return request({
    url: '/hachimiSys/feedback/' + feedbackId,
    method: 'delete'
  })
}
