import request from '@/utils/request'

// 查询宠物图片信息列表
export function listPetimage(query) {
  return request({
    url: '/hachimiSys/petimage/list',
    method: 'get',
    params: query
  })
}

// 查询宠物图片信息详细
export function getPetimage(imageId) {
  return request({
    url: '/hachimiSys/petimage/' + imageId,
    method: 'get'
  })
}

// 新增宠物图片信息
export function addPetimage(data) {
  return request({
    url: '/hachimiSys/petimage',
    method: 'post',
    data: data
  })
}

// 修改宠物图片信息
export function updatePetimage(data) {
  return request({
    url: '/hachimiSys/petimage',
    method: 'put',
    data: data
  })
}

// 删除宠物图片信息
export function delPetimage(imageId) {
  return request({
    url: '/hachimiSys/petimage/' + imageId,
    method: 'delete'
  })
}
