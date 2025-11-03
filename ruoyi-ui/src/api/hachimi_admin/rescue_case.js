import request from '@/utils/request'

// 查询救助案例信息列表
export function listRescue_case(query) {
  return request({
    url: '/hachimi_admin/rescue_case/list',
    method: 'get',
    params: query
  })
}

// 查询救助案例信息详细
export function getRescue_case(caseId) {
  return request({
    url: '/hachimi_admin/rescue_case/' + caseId,
    method: 'get'
  })
}

// 新增救助案例信息
export function addRescue_case(data) {
  return request({
    url: '/hachimi_admin/rescue_case',
    method: 'post',
    data: data
  })
}

// 修改救助案例信息
export function updateRescue_case(data) {
  return request({
    url: '/hachimi_admin/rescue_case',
    method: 'put',
    data: data
  })
}

// 删除救助案例信息
export function delRescue_case(caseId) {
  return request({
    url: '/hachimi_admin/rescue_case/' + caseId,
    method: 'delete'
  })
}
