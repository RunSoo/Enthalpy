<template>
<div>
    <h3>검색 결과</h3>
    <ul class="youtube-list">
        <li v-for="video in videos" :key=video.id.videoId>
            <img :src="video.snippet.thumbnails.default.url">
            {{video.snippet.title}}
        </li>
    </ul>
</div>
</template>

<script>
export default {    
    name: "YoutubeSearchResult",
    props: {
        videos: Array
    }
}
</script>

<style scoped>
    .youtube-list {
        text-align: left;
    }
</style>