import aiohttp
import asyncio
import time

start_time = time.time()


async def main():

    async with aiohttp.ClientSession() as session:

        for number in range(1, 1000):
            res = f'http://localhost:8080/Laborator1-1.0-SNAPSHOT/hello-servlet?mock=false&value=10&key={number}&sync=true'
            async with session.get(res) as resp:
                print(resp)

asyncio.run(main())
print("--- %s seconds ---" % (time.time() - start_time))
