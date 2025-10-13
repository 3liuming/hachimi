import request from '@/utils/request'

// 查询救助案例信息列表
export function listCase(query) {
  return request({
    url: '/hachimiSys/case/list',
    method: 'get',
    params: query
  })
}

// 查询救助案例信息详细
export function getCase(caseId) {
  return request({
    url: '/hachimiSys/case/' + caseId,
    method: 'get'
  })
}

// 新增救助案例信息
export function addCase(data) {
  return request({
    url: '/hachimiSys/case',
    method: 'post',
    data: data
  })
}

// 修改救助案例信息
export function updateCase(data) {
  return request({
    url: '/hachimiSys/case',
    method: 'put',
    data: data
  })
}

// 删除救助案例信息
export function delCase(caseId) {
  return request({
    url: '/hachimiSys/case/' + caseId,
    method: 'delete'
  })
}
