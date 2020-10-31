<template>
    <div id="app">
        <div class="logo">
            <span class="text">smart</span>
            <span class="text">fridge</span>
        </div>
        <router-view/>
        <Navigation/>
    </div>
</template>

<style lang="less">
    #app {
        font-family: 'Open Sans', sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
    }

    * {
        box-sizing: border-box;
        padding: 0;
        margin: 0;
    }

    a {
        outline: none;

        &:active,
        &:focus {
            box-shadow: none;
        }
    }

    .logo {
        display: flex;
        flex-direction: column;
        line-height: 1;
        text-align: center;
        margin: 1rem;

        .text {
            font-size: 1.25rem;
            font-family: 'Sansita Swashed', cursive;
        }
    }

    .container {
        padding: 1rem;
        margin-bottom: 8rem;

        .widget {
            padding: 1rem;

            .headline {
                font-size: 1.1rem;
                color: darken(#a2a2a2, 40);
                font-weight: 600;
            }

            .sub-headline {
                color: darken(#a2a2a2, 50);
                font-size: .85rem;
            }

            .content {
                margin-top: 1rem;
                display: flex;
                flex-direction: column;
            }
        }
    }

    button,
    .btn {
        outline: none;
        border: none;
        margin: 1rem auto;
        padding: .75rem 1rem;
        border-radius: .5rem;
        font-size: .9rem;
        background: #1D3557;
        color: white;
        width: auto;
    }

    .form-group {
        display: flex;
        flex-direction: column;
        margin: .5rem 0;

        label {
            font-size: .9rem;
        }

        input[type=number],
        input[type=date] {
            -webkit-appearance: none;
            width: 50%;
            outline: none;
            font-size: .9rem;
            padding: .5rem .75rem;
        }
    }

    h1 {
        font-size: 1.35rem;
        font-weight: 600;
        color: #404040;
    }

    h4 {
        font-size: .95rem;
        font-weight: normal;
        color: #4d4d4d;
    }
</style>
<script>
    import Navigation from "./components/Navigation";

    export default {
        components: {Navigation}
    }
</script>