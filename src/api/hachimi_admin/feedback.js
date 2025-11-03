import request from '@/utils/request'

// 查询领养后反馈列表
export function listFeedback(query) {
  return request({
    url: '/hachimi_admin/feedback/list',
    method: 'get',
    params: query
  })
}

// 查询领养后反馈详细
export function getFeedback(feedbackId) {
  return request({
    url: '/hachimi_admin/feedback/' + feedbackId,
    method: 'get'
  })
}

// 新增领养后反馈
export function addFeedback(data) {
  return request({
    url: '/hachimi_admin/feedback',
    method: 'post',
    data: data
  })
}

// 修改领养后反馈
export function updateFeedback(data) {
  return request({
    url: '/hachimi_admin/feedback',
    method: 'put',
    data: data
  })
}

// 删除领养后反馈
export function delFeedback(feedbackId) {
  return request({
    url: '/hachimi_admin/feedback/' + feedbackId,
    method: 'delete'
  })
}
