/* Copyright 2006-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import grails.util.GrailsNameUtils
import org.codehaus.groovy.grails.commons.GrailsResourceUtils

includeTargets << new File ( "${grailsHome}/scripts/Init.groovy" )


target(applyNcsTemplate: 'Sets the U of MN template as your default rails template') {

	depends( classpath )
	
	Ant.copy(file: "${ncsWebTemplatePluginDir}/src/templates/ncs.gsp",
		todir: "${basedir}/grails-app/views/layouts")

	updateTemplates()
	updateGspFiles()

}

private void updateGspFiles() {
	ant.echo "Updating Views..."
	def viewsDir = GrailsResourceUtils.VIEWS_DIR_PATH
	updateFolder new File(viewsDir)

}

private void updateFolder(File folder) {
	def p = ~/.*\.gsp/

	folder.eachFileMatch(p){ f ->
		updateGspFile f
	}

	// now we process the sub-folders
	folder.eachDir{ d ->
		updateFolder d
	}
}

private void updateGspFile(File gsp) {

	String layoutMainString = '<meta name="layout" content="main" />'
	String layoutNcsString = '<meta name="layout" content="ncs" />'

	def found = false

	gsp.eachLine{ line ->
		if (line =~ '.*' + layoutMainString + '.*') {
			found = true
		}
	}

	if (found) {
		ant.echo "Updating GSP: ${gsp}"
		def gspName = gsp.path
		def oldFile = new File(gspName + '.old')

		gsp.renameTo( oldFile )
		def newFile = new File(gspName)
		newFile.createNewFile()

		oldFile.eachLine{ line ->
			if (line =~ '.*' + layoutMainString + '.*') {
				newFile.append(line.replace(layoutMainString, layoutNcsString) + '\n')
			} else {
				newFile.append(line + '\n')
			}
		}
	}
}
private void updateTemplates() {

	def scaffoldDir = new File('src/templates/scaffolding')

	if (scaffoldDir.exists()) {
		ant.echo "Applying NCS Web Templates to Scaffolding..."
		updateFolder scaffoldDir
	}
}

setDefaultTarget 'applyNcsTemplate'
