import aiohttp
import asyncio
import time

start_time = time.time()


async def main():

    async with aiohttp.ClientSession() as session:

        for number in range(1, 10):
            pokemon_url = f'http://localhost:8080/Laborator1-1.0-SNAPSHOT/hello-servlet?mock=false&value=1000&key={number}&sync=false'
            async with session.get(pokemon_url) as resp:
                print(resp)

asyncio.run(main())
print("--- %s seconds ---" % (time.time() - start_time))
