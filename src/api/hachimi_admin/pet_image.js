import request from '@/utils/request'

// 查询宠物图片列表
export function listPet_image(query) {
  return request({
    url: '/hachimi_admin/pet_image/list',
    method: 'get',
    params: query
  })
}

// 查询宠物图片详细
export function getPet_image(imageId) {
  return request({
    url: '/hachimi_admin/pet_image/' + imageId,
    method: 'get'
  })
}

// 新增宠物图片
export function addPet_image(data) {
  return request({
    url: '/hachimi_admin/pet_image',
    method: 'post',
    data: data
  })
}

// 修改宠物图片
export function updatePet_image(data) {
  return request({
    url: '/hachimi_admin/pet_image',
    method: 'put',
    data: data
  })
}

// 删除宠物图片
export function delPet_image(imageId) {
  return request({
    url: '/hachimi_admin/pet_image/' + imageId,
    method: 'delete'
  })
}
