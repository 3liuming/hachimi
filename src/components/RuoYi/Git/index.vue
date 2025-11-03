<template>
  <div>
    <svg-icon icon-class="github" @click="goto" />
  </div>
</template>

<script>
export default {
  name: 'RuoYiGit',
  data() {
    return {
      url: 'https://github.com/3liuming/hachimi.git'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>
