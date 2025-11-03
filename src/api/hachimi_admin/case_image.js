import request from '@/utils/request'

// 查询案例图片列表
export function listCase_image(query) {
  return request({
    url: '/hachimi_admin/case_image/list',
    method: 'get',
    params: query
  })
}

// 查询案例图片详细
export function getCase_image(caseImageId) {
  return request({
    url: '/hachimi_admin/case_image/' + caseImageId,
    method: 'get'
  })
}

// 新增案例图片
export function addCase_image(data) {
  return request({
    url: '/hachimi_admin/case_image',
    method: 'post',
    data: data
  })
}

// 修改案例图片
export function updateCase_image(data) {
  return request({
    url: '/hachimi_admin/case_image',
    method: 'put',
    data: data
  })
}

// 删除案例图片
export function delCase_image(caseImageId) {
  return request({
    url: '/hachimi_admin/case_image/' + caseImageId,
    method: 'delete'
  })
}
