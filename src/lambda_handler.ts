import { APIGatewayProxyEvent, Context, APIGatewayProxyResult } from "aws-lambda";
import model from "./models";
import {startConnection} from './database';

export async function getUsers(event: APIGatewayProxyEvent, context: Context): Promise<APIGatewayProxyResult> {
    context.callbackWaitsForEmptyEventLoop = false;
    await startConnection();
    const users = await model.find();
    let response:APIGatewayProxyResult = {
        statusCode: 200,
        body: JSON.stringify(users)
    };
    return response;​
}
