import request from '@/utils/request'

// 查询案例图片信息列表
export function listCaseimage(query) {
  return request({
    url: '/hachimiSys/caseimage/list',
    method: 'get',
    params: query
  })
}

// 查询案例图片信息详细
export function getCaseimage(caseImageId) {
  return request({
    url: '/hachimiSys/caseimage/' + caseImageId,
    method: 'get'
  })
}

// 新增案例图片信息
export function addCaseimage(data) {
  return request({
    url: '/hachimiSys/caseimage',
    method: 'post',
    data: data
  })
}

// 修改案例图片信息
export function updateCaseimage(data) {
  return request({
    url: '/hachimiSys/caseimage',
    method: 'put',
    data: data
  })
}

// 删除案例图片信息
export function delCaseimage(caseImageId) {
  return request({
    url: '/hachimiSys/caseimage/' + caseImageId,
    method: 'delete'
  })
}
