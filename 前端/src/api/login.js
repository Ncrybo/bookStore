import request from '@/network'

export function login(data) {
  return request({
    url: '/login',
    method: 'post',
    data: data
  })
}