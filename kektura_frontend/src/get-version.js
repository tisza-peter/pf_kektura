const util = require('util');
const exec = util.promisify(require('child_process').exec);
const fs = require('fs');
const versionFile = './version.json';
const versionJson = {};

let gitTag = '0.0.0';
let commitDate = '2022-01-01';
let commitHash = '4a65efa2';

setCommitHash()
	.then(() => (versionJson.commitHash = commitHash))
	.then(() => setGitTag())
	.then(() => (versionJson.appVersion = gitTag))
	.then(() => setCommitDate())
	.then(() => (versionJson.commitDate = commitDate))
	.then(() => updateVersionJson(versionJson));

async function getGitData(command, defaultValue) {
	try {
		const { stdout } = await exec(command);
		return stdout.replace(/(\n)/gm, '');
	} catch (error) {
		console.error(error.message);
		return defaultValue;
	}
}

async function setGitTag() {
	let cmd = 'git describe --tags `git rev-list --tags --max-count=1`';
	gitTag = await getGitData(cmd, commitHash);
}

async function setCommitDate() {
	let cmd = 'git log -1 --format=%cd --date=format:"%Y-%m-%d" HEAD';
	commitDate = await getGitData(cmd, commitDate);
}

async function setCommitHash() {
	let cmd = 'git rev-parse --short HEAD';
	commitHash = await getGitData(cmd, commitHash);
}

function updateVersionJson(data) {
	data = JSON.stringify(data);
	fs.writeFile(versionFile, data, (error) => {
		if (error) console.error(error);
	});
}
